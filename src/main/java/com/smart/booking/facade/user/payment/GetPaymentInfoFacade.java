package com.smart.booking.facade.user.payment;

import com.smart.booking.domain.payment.entity.Payment;
import com.smart.booking.domain.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GetPaymentInfoFacade {

    private PaymentService paymentInfoService;

    public Payment exceuete(String paymentId) {
        return paymentInfoService.getPaymentInfo(paymentId);
    }
}
