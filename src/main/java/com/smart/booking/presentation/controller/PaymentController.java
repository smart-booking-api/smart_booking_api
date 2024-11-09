package com.smart.booking.presentation.controller;

import com.smart.booking.facade.dto.payment.CompletePaymentRequestDto;
import com.smart.booking.presentation.controller.endPoint.PaymentEndPoint;
import com.smart.booking.facade.dto.payment.SavePaymentTrackingHistoryRequestDto;
import com.smart.booking.facade.paymentGateway.CompletePaymentFacade;
import com.smart.booking.facade.user.payment.PreparePaymentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final CompletePaymentFacade completePaymentFacade;
    private final PreparePaymentFacade preparePaymentFacade;

    /**
     * PG -> 결제 승인
     * 결제 승인 처리
     */
    @PostMapping(PaymentEndPoint.PAYMENT_COMPLETE_URL)
    public void completePayment(@Validated @RequestBody CompletePaymentRequestDto request) throws Exception {
        completePaymentFacade.exceuete(request);
    }

    /**
     * PG -> 결제
     * 결제창 접근
     */
    @PostMapping(PaymentEndPoint.PAYMENT_PREPARE_URL)
    public void preparePayment(@Validated @RequestBody SavePaymentTrackingHistoryRequestDto request) throws Exception {
        preparePaymentFacade.exceuete(request);
    }

}
