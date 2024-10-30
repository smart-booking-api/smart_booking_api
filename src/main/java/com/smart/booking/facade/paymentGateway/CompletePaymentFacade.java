package com.smart.booking.facade.paymentGateway;

import com.smart.booking.facade.dto.payment.CompletePaymentRequestDto;
import com.smart.booking.domain.common.facade.Facade;
import com.smart.booking.domain.payment.dto.SavePaymentDto;
import com.smart.booking.domain.payment.dto.SavePaymentHistoryDto;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.payment.service.PaymentInfoService;
import com.smart.booking.domain.payment.service.PaymentHistoryService;
import com.smart.booking.domain.payment.service.PaymentTrackingHistoryService;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.facade.eventPublisher.ReservationSaveEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CompletePaymentFacade implements Facade<CompletePaymentRequestDto, Void> {

    private final PaymentInfoService paymentInfoService;
    private final PaymentTrackingHistoryService paymentTrackingInfoService;
    private final PaymentHistoryService paymentLogService;
    private final ReservationSaveEventPublisher applicationEventPublisher;


    /**
     * 결제 완료 프로세스
     *
     * @return
     */

    @Override
    @Transactional
    public Void exceuete(CompletePaymentRequestDto dto) throws Exception {
        //TODO teeBox service에 id로 조회 요청
        TeeBox teeBox = null;

        //1. 결제 완료 정보 저장
        var savePaymentDto = new SavePaymentDto(dto.amount(), PaymentStatus.COMPLETE, teeBox);
        var payment = paymentInfoService.savePaymentCompleteInfo(savePaymentDto);

        //TODO 2. 파트너별 payment 저장

        //3. 결제-트랙킹 정보 업데이트
        paymentTrackingInfoService.matchPaymentAndTrackingInfo(payment.getPaymentId(), dto.trackingId());

        //4. 결제 완료 로그 저장
        var historyDto = new SavePaymentHistoryDto(payment, payment.getTotalAmount(), payment.getPaymentStatus());
        paymentLogService.savePaymentCompleteRequestLog(historyDto);

        //5. 예약 생성 요청
        applicationEventPublisher.publish(payment.getPaymentId());
        return null;
    }
}
