package com.smart.booking.domain.payment.service;

public interface PaymentInfoService {
    /**
     * 결제 정보 조회
     * request : reservationId
     * response : 예약수수료 (주중/주말)
     * */
    Object getPaymentInfo(Long paymentId);
}
