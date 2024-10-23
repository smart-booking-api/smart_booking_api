package com.smart.booking.domain.payment.service;

public interface PaymentAmountService {
    /**
     * 결제금액 정보 조회
     * request : paymentId
     * response :
     * 공급가액
     * 면세가액
     * 부가세액
     * 과세제외액
     * */
    Object getPaymentAmount(Long paymentId);
}
