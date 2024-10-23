package com.smart.booking.domain.payment.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class PaymentLogServiceImpl implements PaymentLogService {


    @Override
    public void savePaymentRequestLog(Object requestLog) {

    }

    @Override
    public void savePaymentCancelRequestLog(@NonNull String paymentId) {

    }
}
