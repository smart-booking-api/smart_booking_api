package com.smart.booking.facade.pg;

import com.smart.booking.facade.dto.payment.CompletePaymentRequestDto;
import com.smart.booking.domain.common.facade.Facade;
import com.smart.booking.domain.payment.dto.SavePaymentDto;
import com.smart.booking.domain.payment.dto.SavePaymentHistoryDto;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.payment.service.PaymentInfoService;
import com.smart.booking.domain.payment.service.PaymentHistoryService;
import com.smart.booking.domain.payment.service.PaymentTrackingHistoryService;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.facade.event.dto.CompletePaymentEventDto;
import com.smart.booking.facade.event.publisher.CompletePaymentEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CompletePaymentFacade {

    private final PaymentInfoService paymentInfoService;
    private final PaymentTrackingHistoryService paymentTrackingInfoService;
    private final PaymentHistoryService paymentLogService;
    private final CompletePaymentEventPublisher reservationSaveEventPublisher;


    /**
     * 결제 완료 프로세스
     *
     * @return
     */

    @Transactional
    public void exceuete(CompletePaymentRequestDto dto) {
        //TODO teeBox service에 id로 조회 요청
        TeeBox teeBox = null;

        var paymentInfo = paymentInfoService.getExternalPaymentInfo(dto.merchantUid());

        //1. 결제 완료 정보 저장
        var savePaymentDto = new SavePaymentDto(paymentInfo.reservationFee(), PaymentStatus.COMPLETE, teeBox);
        var payment = paymentInfoService.savePaymentCompleteInfo(savePaymentDto);

        //TODO 2. 파트너별 payment 저장

        //3. 결제-트랙킹 정보 업데이트
        paymentTrackingInfoService.matchPaymentAndTrackingInfo(payment.getPaymentId(), dto.trackingId());

        //4. 결제 완료 로그 저장
        var historyDto = new SavePaymentHistoryDto(payment, paymentInfo.reservationFee(), payment.getPaymentStatus());
        paymentLogService.savePaymentCompleteRequestLog(historyDto);

        //5. 예약 생성 요청
        reservationSaveEventPublisher.publish(
            CompletePaymentEventDto.builder()
                .memberId(dto.memberId())
                .merchantUid(dto.merchantUid())
                .trackingId(dto.trackingId())
                .teeBoxId(dto.teeBoxId())
                .timeTableId(dto.timeTableId())
                .amount(dto.amount())
                .reservationUserName(dto.reservationUserName())
                .reservationUserPhoneNum(dto.reservationUserPhoneNum())
                .build()
        );
    }
}
