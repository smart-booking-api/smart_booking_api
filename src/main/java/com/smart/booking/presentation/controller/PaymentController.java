package com.smart.booking.presentation.controller;

import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.common.resolver.MemberContext;
import com.smart.booking.facade.dto.payment.CancelPaymentRequestDto;
import com.smart.booking.facade.dto.payment.CompletePaymentRequestDto;
import com.smart.booking.presentation.controller.endPoint.PaymentEndPoint;
import com.smart.booking.facade.dto.payment.SavePaymentTrackingHistoryRequestDto;
import com.smart.booking.facade.pg.CompletePaymentFacade;
import com.smart.booking.facade.user.payment.CancelPaymentFacade;
import com.smart.booking.facade.user.payment.PreparePaymentFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "사용자 결제", description = "사용자 결제 컨트롤러")
public class PaymentController {

    private final CompletePaymentFacade completePaymentFacade;
    private final CancelPaymentFacade cancelPaymentFacade;
    private final PreparePaymentFacade preparePaymentFacade;

    /**
     * PG -> 결제 승인
     * 결제 승인 처리
     */
    @Operation(security = {
        @SecurityRequirement(name = "accessToken")}, summary = "portOne 결제 완료 webHook", description = "포트원에서 결제 완료후 결과값 전송받아 결제 후 예약 생성 요청")
    @PostMapping(PaymentEndPoint.PAYMENT_COMPLETE_URL)
    public void completePayment(
        @Valid @RequestBody CompletePaymentRequestDto request) {
        completePaymentFacade.exceuete(request);
    }

    /**
     * 사용자 -> 결제 취소
     * 결제 취소
     */
    @Operation(security = {@SecurityRequirement(name = "accessToken")}, summary = "사용자 결제 취소", description = "portOne으로 결제취소 요청")
    @PostMapping(PaymentEndPoint.PAYMENT_CANCEL_URL)
    public void cancelPayment(
        @Valid @RequestBody CancelPaymentRequestDto request, @MemberContext MemberContextDto memberContextDto) {
        cancelPaymentFacade.exceuete(request, memberContextDto);
    }

    /**
     * PG -> 결제
     * 결제창 접근
     */
    @Operation(security = {
        @SecurityRequirement(name = "accessToken")}, summary = "사용자 결제창 접근 로그 저장", description = "사용자가 결제창에 진입한 로그를 저장하여, 결제까지의 추적을 가능하게 한다.")
    @PostMapping(PaymentEndPoint.PAYMENT_PREPARE_URL)
    public void preparePayment(
        @Valid @RequestBody SavePaymentTrackingHistoryRequestDto request,
        @MemberContext MemberContextDto memberContextDto
    ) {
        preparePaymentFacade.exceuete(request, memberContextDto);
    }

}
