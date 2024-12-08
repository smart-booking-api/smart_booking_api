package com.smart.booking.facade.user.payment;

import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.domain.payment.dto.SavePaymentTrackingHistoryDto;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.payment.service.PaymentTrackingHistoryService;
import com.smart.booking.facade.dto.payment.SavePaymentTrackingHistoryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PreparePaymentFacade {

    private final PaymentTrackingHistoryService paymentTrackingHistoryService;

    /**
     * 결제 요청 프로세스
     *
     * @return
     */

    @Transactional
    public void exceuete(SavePaymentTrackingHistoryRequestDto dto, MemberContextDto memberContextDto) {
        // tee box 유효성 검사?
        //TODO teeBox 유효성 검사
        //TODO time table 유효성 검사

        //1. 결제 대기 중 tracking save
        paymentTrackingHistoryService.saveTrackingHistory(
            SavePaymentTrackingHistoryDto.builder()
                .memberId(memberContextDto.getMemberId())
                .trackingId(dto.trackingId())
                .teeBoxId(dto.teeBoxId())
                .timeTableId(dto.timeTableId())
                .totalAmount(dto.amount())
                .paymentStatus(PaymentStatus.PENDING)
                .build()
        );
    }
}
