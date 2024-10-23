package com.smart.booking.domain.payment.service;

public interface PaymentRequestLogService {
    /**
     * 결제 요청 로그 저장
     * request :
     * tracking id
     * tBox Id
     * timeTable id
     * 결제정보
     * */
    void savePaymentRequestLog(Object requestLog);
}
