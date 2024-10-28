package com.smart.booking.domain.payment.service;

import com.smart.booking.domain.payment.dto.SavePaymentHistoryDto;

public interface PaymentHistoryService {
    /**
     * 결제 요청 로그 저장
     * request :
     * tracking id
     * tBox Id
     * timeTable id
     * 결제정보
     * */
    void savePaymentCompleteRequestLog(SavePaymentHistoryDto historyDto);

    /**
     * 결제 취소 로그 저장
     * request :
     * payment id
     * */
    void savePaymentCancelRequestLog(String paymentId);
}
