package com.smart.booking.domain.payment.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService{

    @Override
    public Object getPaymentInfo(@NonNull String paymentId) {
        return null;
    }

    @Override
    public Object savePaymentCompleteInfo(String trackingId, String teeBoxId, String timeTableId) {
        return null;
    }

    @Override
    public void savePaymentCancelInfo(String paymentId) {

    }

}
