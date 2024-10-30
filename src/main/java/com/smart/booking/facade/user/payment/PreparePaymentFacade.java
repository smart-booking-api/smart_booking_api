package com.smart.booking.facade.user.payment;

import com.smart.booking.domain.payment.dto.SavePaymentTrackingHistoryDto;
import com.smart.booking.facade.dto.payment.CompletePaymentRequestDto;
import com.smart.booking.domain.common.facade.Facade;
import com.smart.booking.domain.payment.dto.SavePaymentDto;
import com.smart.booking.domain.payment.dto.SavePaymentHistoryDto;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.payment.service.PaymentHistoryService;
import com.smart.booking.domain.payment.service.PaymentInfoService;
import com.smart.booking.domain.payment.service.PaymentTrackingHistoryService;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.facade.dto.payment.SavePaymentTrackingHistoryRequestDto;
import com.smart.booking.facade.eventPublisher.ReservationSaveEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PreparePaymentFacade implements Facade<SavePaymentTrackingHistoryRequestDto, Void> {

    private final PaymentTrackingHistoryService paymentTrackingHistoryService;

    /**
     * 결제 요청 프로세스
     *
     * @return
     */

    @Override
    @Transactional
    public Void exceuete(SavePaymentTrackingHistoryRequestDto dto) throws Exception {
        // tee box 유효성 검사?
        //TODO teeBox 유효성 검사
        //TODO time table 유효성 검사

        //1. 결제 대기 중 tracking save
        paymentTrackingHistoryService.saveTrackingHistory(
            SavePaymentTrackingHistoryDto.builder()
                .trackingId(dto.trackingId())
                .teeBoxId(dto.teeBoxId())
                .timeTableId(dto.timeTableId())
                .totalAmount(dto.amount())
                .paymentStatus(PaymentStatus.PENDING)
                .build()
        );
        return null;
    }
}
