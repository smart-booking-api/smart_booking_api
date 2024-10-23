package com.smart.booking.facade.payment;

import com.smart.booking.domain.common.facade.Facade;
import com.smart.booking.domain.payment.service.PaymentInfoService;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GetPaymentInfoFacade implements Facade<String, Object> {

    private PaymentInfoService paymentInfoService;

    @Override
    public Object exceuete(String paymentId) {
        return paymentInfoService.getPaymentInfo(paymentId);
    }
}
