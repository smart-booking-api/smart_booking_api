package com.smart.booking.controller;

import com.smart.booking.common.dto.MemberContext;
import com.smart.booking.facade.dto.payment.CancelPaymentRequestDto;
import com.smart.booking.facade.dto.payment.CompletePaymentRequestDto;
import com.smart.booking.controller.endPoint.PaymentEndPoint;
import com.smart.booking.facade.dto.payment.SavePaymentTrackingHistoryRequestDto;
import com.smart.booking.facade.pg.CompletePaymentFacade;
import com.smart.booking.facade.user.payment.CancelPaymentFacade;
import com.smart.booking.facade.user.payment.PreparePaymentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final CompletePaymentFacade completePaymentFacade;
    private final CancelPaymentFacade cancelPaymentFacade;
    private final PreparePaymentFacade preparePaymentFacade;

    /**
     * PG -> 결제 승인
     * 결제 승인 처리
     */
    @PostMapping(PaymentEndPoint.PAYMENT_COMPLETE_URL)
    public void completePayment(
        @Validated @RequestBody CompletePaymentRequestDto request) {
        completePaymentFacade.exceuete(request);
    }

    /**
     * 사용자 -> 결제 취소
     * 결제 취소
     */
    @PostMapping(PaymentEndPoint.PAYMENT_CANCEL_URL)
    public void cancelPayment(
        @Validated @RequestBody CancelPaymentRequestDto request, MemberContext memberContext) {
        cancelPaymentFacade.exceuete(request, memberContext);
    }

    /**
     * PG -> 결제
     * 결제창 접근
     */
    @PostMapping(PaymentEndPoint.PAYMENT_PREPARE_URL)
    public void preparePayment(@Validated @RequestBody SavePaymentTrackingHistoryRequestDto request, MemberContext memberContext) {
        preparePaymentFacade.exceuete(request, memberContext);
    }

}
