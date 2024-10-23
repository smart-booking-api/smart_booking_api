package com.smart.booking.domain.payment.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentTrackingInfoServiceImpl implements PaymentTrackingInfoService{

    @Override
    public Object getTrackingInfo(String trackingInfo) {
        return null;
    }

    @Override
    public void saveTrackingInfo(Object trackingInfo) {

    }

    @Override
    public void matchPaymentAndTrackingInfo(String trackingId, String paymentId) {

    }
}
