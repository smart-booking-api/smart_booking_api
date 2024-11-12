package com.smart.booking.facade.user.payment;

import com.smart.booking.domain.common.facade.Facade;
import com.smart.booking.domain.payment.entity.Payment;
import com.smart.booking.domain.payment.service.PaymentInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GetPaymentInfoFacade {

    private PaymentInfoService paymentInfoService;

    public Payment exceuete(String paymentId) {
        return paymentInfoService.getPaymentInfo(paymentId);
    }
}
