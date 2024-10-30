package com.smart.booking.domain.payment.service;

import com.smart.booking.domain.payment.dto.SavePaymentDto;
import com.smart.booking.domain.payment.entity.Payment;

public interface PaymentInfoService {
    /**
     * 결제 정보 조회
     * request : paymentId
     * response : 예약수수료 (주중/주말)
     * */
    Object getPaymentInfo(String paymentId);

    /**
     * 결제 완료 저장
     * request :
     * tracking id
     * teeBox id
     * timeTable id
     * response :payment id
     * */
    Payment savePaymentCompleteInfo(SavePaymentDto request);

    /**
     * 결제 취소 저장
     * request : payment id
     * */
    void savePaymentCancelInfo(String paymentId);

}
