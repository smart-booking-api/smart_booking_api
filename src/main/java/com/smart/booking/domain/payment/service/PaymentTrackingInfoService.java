package com.smart.booking.domain.payment.service;

public interface PaymentTrackingInfoService {
    /**
     * 결제 트랙킹 정보 저장
     * request :
     * tracking id
     * tBox Id
     * timeTable id
     * */
    void saveTrackingInfo(Object trackingInfo);
    void updateTrackingInfo(Object trackingInfo);
}
