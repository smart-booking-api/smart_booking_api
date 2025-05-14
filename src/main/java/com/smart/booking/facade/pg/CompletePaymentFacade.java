package com.smart.booking.facade.pg;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.payment.entity.PaymentPartnerShare;
import com.smart.booking.domain.payment.service.PaymentPartnerShareService;
import com.smart.booking.domain.tee_box.service.TeeBoxCommonService;
import com.smart.booking.external.portOne.model.ExternalCustomDataDto;
import com.smart.booking.facade.dto.payment.CompletePaymentRequestDto;
import com.smart.booking.domain.payment.dto.SavePaymentDto;
import com.smart.booking.domain.payment.dto.SavePaymentHistoryDto;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.payment.service.PaymentService;
import com.smart.booking.domain.payment.service.PaymentHistoryService;
import com.smart.booking.domain.payment.service.PaymentTrackingHistoryService;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.facade.dto.reservation.CardReceiptDto.PaymentInfo;
import com.smart.booking.facade.event.dto.CompletePaymentEventDto;
import com.smart.booking.facade.event.dto.FailPaymentEventDto;
import com.smart.booking.facade.event.publisher.CompletePaymentEventPublisher;
import com.smart.booking.facade.event.publisher.FailPaymentEventPublisher;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CompletePaymentFacade {

    private final PaymentService paymentInfoService;
    private final PaymentPartnerShareService paymentPartnerShareService;
    private final PaymentTrackingHistoryService paymentTrackingInfoService;
    private final PaymentHistoryService paymentLogService;
    private final CompletePaymentEventPublisher reservationSaveEventPublisher;
    private final FailPaymentEventPublisher reservationFailPaymentEventPublisher;
    private final TeeBoxCommonService teeBoxCommonService;


    private final Map<PaymentStatus, Consumer<ExternalCustomDataDto>> paymentStatusEventDispatcher = Map.of(
        PaymentStatus.COMPLETE, this::publishSuccessEvent,
        PaymentStatus.CANCEL, this::publishFailEvent
    );


    /**
     * 결제 완료 프로세스
     *
     * @return
     */

    @Transactional
    public void exceuete(CompletePaymentRequestDto dto) {
        //0. validation
        var payment = paymentInfoService.getPaymentInfo(dto.impUid(), dto.merchantUid());
        if (payment != null) {
            throw new CommonException(ResponseCode.ALREADY_SAVED_PAYMENT_ERROR);
        }

        var paymentInfo = paymentInfoService.getExternalPaymentCustomData(dto.merchantUid());
        // 실패 여부 판단
        var paymentStatus = (paymentInfo.failedAt() != 0)
            ? PaymentStatus.CANCEL
            : PaymentStatus.COMPLETE;

        var trackingInfo = paymentTrackingInfoService.getTrackingInfo(paymentInfo.trackingId());
        if (trackingInfo == null) {
            throw new CommonException(ResponseCode.NO_DATA_SAVED_PAYMENT_TRACKING_INFO);
        }

        var teeBox = teeBoxCommonService.getTeeBoxById(paymentInfo.teeBoxId());

        //1. 결제 완료 정보 저장
        var savePaymentDto = new SavePaymentDto(
            dto.impUid(),
            dto.merchantUid(),
            paymentInfo.reservationFee(),
            paymentStatus,
            teeBox
        );
        var savedPayment = paymentInfoService.savePaymentCompleteInfo(savePaymentDto);

        //2. 파트너별 payment 저장
        List<PaymentPartnerShare> paymentPartnerShares = new ArrayList<>();
        for (var teeBoxShare : teeBox.getShares()) {
            paymentPartnerShares.add(
                PaymentPartnerShare.builder()
                    .partner(teeBoxShare.getPartner())
                    .payment(savedPayment)
                    .totalAmount(savedPayment.getTotalAmount() / teeBoxShare.getShare())
                    .build()
            );
        }
        paymentPartnerShareService.saveAll(paymentPartnerShares);

        //3. 결제-트랙킹 정보 업데이트
        paymentTrackingInfoService.matchPaymentAndTrackingInfo(savedPayment.getPaymentId(), paymentInfo.trackingId());

        //4. 결제 완료 로그 저장
        var historyDto = new SavePaymentHistoryDto(
            savedPayment,
            paymentInfo.reservationFee(),
            savedPayment.getPaymentStatus(),
            savedPayment.getPaymentStatus().getValue()
        );
        paymentLogService.savePaymentHistoryLog(historyDto);

        //5. 예약 생성 요청
        paymentStatusEventDispatcher
            .getOrDefault(paymentStatus, pi -> {
            })
            .accept(paymentInfo);
    }

    private void publishSuccessEvent(ExternalCustomDataDto paymentInfo) {
        reservationSaveEventPublisher.publish(
            CompletePaymentEventDto.builder()
                .memberId(paymentInfo.memberId())
                .trackingId(paymentInfo.trackingId())
                .teeBoxId(paymentInfo.teeBoxId())
                .startTimeTableId(paymentInfo.startTimeTableId())
                .endTimeTableId(paymentInfo.endTimeTableId())
                .amount(paymentInfo.reservationFee())
                .build()
        );
    }

    private void publishFailEvent(ExternalCustomDataDto paymentInfo) {
        reservationFailPaymentEventPublisher.publish(
            FailPaymentEventDto.builder()
                .memberId(paymentInfo.memberId())
                .trackingId(paymentInfo.trackingId())
                .failedAt(paymentInfo.failedAt())
                .failReason(paymentInfo.failReason())
                .build()
        );
    }

}
