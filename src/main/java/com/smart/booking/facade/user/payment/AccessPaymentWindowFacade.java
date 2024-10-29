package com.smart.booking.facade.user.payment;

import com.smart.booking.domain.payment.dto.SavePaymentTrackingHistoryDto;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.payment.entity.PaymentTrackingHistory;
import com.smart.booking.domain.common.facade.Facade;
import com.smart.booking.domain.payment.service.PaymentTrackingHistoryService;
import com.smart.booking.facade.dto.payment.SavePaymentTrackingHistoryRequestDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccessPaymentWindowFacade implements Facade<SavePaymentTrackingHistoryRequestDto, Void> {

    private final PaymentTrackingHistoryService paymentTrackingHistoryService;

    /**
     * 결제창 진입 프로세스
     *
     * @return
     */

    @Override
    @Transactional
    public Void exceuete(@NonNull SavePaymentTrackingHistoryRequestDto dto) throws Exception {
        // tee box 유효성 검사?
        // time table 유효성 검사?

        //1. tracking 정보 저장
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
