package com.smart.booking.controller;

import com.smart.booking.common.exception.CommonException;
import com.smart.booking.controller.dto.CompletePaymentRequestDto;
import com.smart.booking.controller.endPoint.PaymentEndPoint;
import com.smart.booking.facade.payment.CompletePaymentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final CompletePaymentFacade completePaymentFacade;

    /**
     * PG -> 결제 승인
     * 결제 승인 처리
     */
    @PostMapping(PaymentEndPoint.PAYMENT_COMPLETE_URL)
    public void completePayment(@Validated @RequestBody CompletePaymentRequestDto request) throws CommonException {
        completePaymentFacade.exceuete(request);
    }

}
