package com.smart.booking.facade.pg;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.payment.entity.Payment;
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
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompletePaymentFacade {

    private final PaymentService paymentInfoService;
    private final PaymentPartnerShareService paymentPartnerShareService;
    private final PaymentTrackingHistoryService paymentTrackingInfoService;
    private final PaymentHistoryService paymentLogService;
    private final CompletePaymentEventPublisher reservationSaveEventPublisher;
    private final FailPaymentEventPublisher reservationFailPaymentEventPublisher;
    private final TeeBoxCommonService teeBoxCommonService;

    private static final Consumer<ExternalCustomDataDto> NO_OP_CONSUMER = dto -> {
    };

    private final Map<PaymentStatus, BiConsumer<Payment, ExternalCustomDataDto>> paymentStatusEventDispatcher = Map.of(
        PaymentStatus.COMPLETE, this::publishSuccessEvent,
        PaymentStatus.CANCEL, this::publishFailEvent
    );

    @Transactional
    public void execute(CompletePaymentRequestDto dto) {
        validatePaymentNotAlreadySaved(dto);

        ExternalCustomDataDto paymentInfo = getPaymentInfo(dto);
        TeeBox teeBox = getTeeBox(paymentInfo);

        Payment savedPayment = savePaymentInfo(dto, paymentInfo, teeBox);
        savePartnerShareInfo(savedPayment, teeBox);

        matchTrackingInfo(savedPayment, paymentInfo);
        savePaymentLog(savedPayment, paymentInfo);

        dispatchEvent(savedPayment, paymentInfo);
    }

    private void validatePaymentNotAlreadySaved(CompletePaymentRequestDto dto) {
        Payment payment = paymentInfoService.getPaymentInfo(dto.impUid(), dto.merchantUid());
        if (payment != null) {
            throw new CommonException(ResponseCode.ALREADY_SAVED_PAYMENT_ERROR);
        }
    }

    private ExternalCustomDataDto getPaymentInfo(CompletePaymentRequestDto dto) {
        ExternalCustomDataDto paymentInfo = paymentInfoService.getExternalPaymentCustomData(dto.merchantUid());
        if (paymentTrackingInfoService.getTrackingInfo(paymentInfo.trackingId()) == null) {
            throw new CommonException(ResponseCode.NO_DATA_SAVED_PAYMENT_TRACKING_INFO);
        }
        return paymentInfo;
    }

    private TeeBox getTeeBox(ExternalCustomDataDto paymentInfo) {
        return teeBoxCommonService.getTeeBoxById(paymentInfo.teeBoxId());
    }

    private Payment savePaymentInfo(CompletePaymentRequestDto dto, ExternalCustomDataDto paymentInfo, TeeBox teeBox) {
        SavePaymentDto savePaymentDto = new SavePaymentDto(
            dto.impUid(),
            dto.merchantUid(),
            paymentInfo.reservationFee(),
            paymentInfo.getPaymentStatus(),
            teeBox
        );
        return paymentInfoService.savePaymentCompleteInfo(savePaymentDto);
    }

    private void savePartnerShareInfo(Payment payment, TeeBox teeBox) {
        List<PaymentPartnerShare> shares = teeBox.getShares().stream()
            .map(share -> PaymentPartnerShare.builder()
                .partner(share.getPartner())
                .payment(payment)
                .totalAmount(payment.getTotalAmount() / share.getShare())
                .build())
            .toList();
        paymentPartnerShareService.saveAll(shares);
    }

    private void matchTrackingInfo(Payment payment, ExternalCustomDataDto paymentInfo) {
        paymentTrackingInfoService.matchPaymentAndTrackingInfo(payment.getPaymentId(), paymentInfo.trackingId());
    }

    private void savePaymentLog(Payment payment, ExternalCustomDataDto paymentInfo) {
        SavePaymentHistoryDto historyDto = new SavePaymentHistoryDto(
            payment,
            paymentInfo.reservationFee(),
            payment.getPaymentStatus(),
            payment.getPaymentStatus().getValue()
        );
        paymentLogService.savePaymentHistoryLog(historyDto);
    }

    private void dispatchEvent(Payment payment, ExternalCustomDataDto paymentInfo) {
        paymentStatusEventDispatcher
            .getOrDefault(payment.getPaymentStatus(), (paymentParma, externalDto) -> NO_OP_CONSUMER.accept(externalDto))
            .accept(payment, paymentInfo);
    }

    private void publishSuccessEvent(Payment payment, ExternalCustomDataDto paymentInfo) {
        reservationSaveEventPublisher.publish(
            CompletePaymentEventDto.builder()
                .paymentId(payment.getPaymentId())
                .memberId(paymentInfo.memberId())
                .trackingId(paymentInfo.trackingId())
                .teeBoxId(paymentInfo.teeBoxId())
                .startTimeTableId(paymentInfo.startTimeTableId())
                .endTimeTableId(paymentInfo.endTimeTableId())
                .amount(paymentInfo.reservationFee())
                .build()
        );
    }

    private void publishFailEvent(Payment payment, ExternalCustomDataDto paymentInfo) {
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
