package com.smart.booking.domain.payment.service;

import com.smart.booking.common.exception.CommonException;

public interface PaymentTrackingInfoService {

    /**
     * 결제 트랙킹 정보 조회
     * request :
     * tracking id
     * */
    Object getTrackingInfo(String trackingInfo);

    /**
     * 결제 트랙킹 정보 저장
     * request :
     * tracking id
     * tBox Id
     * timeTable id
     * */
    void saveTrackingInfo(Object trackingInfo);

    /**
     * 결제 매칭 업데이트
     * request :
     * tracking id
     * payment id
     * */
    void matchPaymentAndTrackingInfo(String trackingId, String paymentId) throws Exception;
}
