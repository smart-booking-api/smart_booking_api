package com.smart.booking.domain.payment.service;

public interface PaymentResultService {
    /**
     * 결제 결과 저장
     * request :
     * tracking id
     * tBox Id
     * timeTable id
     * 결과
     * */
    void savePaymentResult(Object result);
}
