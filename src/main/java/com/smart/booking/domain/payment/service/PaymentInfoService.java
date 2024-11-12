package com.smart.booking.domain.payment.service;

import com.smart.booking.domain.external.dto.CancelPaymentRequestDto;
import com.smart.booking.domain.external.dto.ExternalCustomDataDto;
import com.smart.booking.domain.external.dto.ExternalPaymentInfoResponseDto;
import com.smart.booking.domain.external.dto.PaymentAnnotationDto;
import com.smart.booking.domain.payment.dto.SavePaymentDto;
import com.smart.booking.domain.payment.entity.Payment;

public interface PaymentInfoService {

    /**
     * 결제 정보 조회
     * request : paymentId
     * response : 예약수수료 (주중/주말)
     */
    Payment getPaymentInfo(String paymentId);

    /**
     * 결제 정보 조회
     * request : merchantUid
     * response : ExternalPaymentInfoResponseDto
     */
    ExternalCustomDataDto getExternalPaymentInfo(String impUid);


    /**
     * 결제 정보 조회
     * request : CancelPaymentRequestDto
     */
    PaymentAnnotationDto cancelPayment(CancelPaymentRequestDto request);

    /**
     * 결제 완료 저장
     * request :
     * tracking id
     * teeBox id
     * timeTable id
     * response :payment id
     */
    Payment savePaymentCompleteInfo(SavePaymentDto request);

    /**
     * 결제 취소 저장
     * request : payment
     */
    void savePaymentCancelInfo(Payment payment);

}
