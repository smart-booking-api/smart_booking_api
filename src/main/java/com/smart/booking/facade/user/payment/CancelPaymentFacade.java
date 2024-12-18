package com.smart.booking.facade.user.payment;

import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.external.portOne.model.CancelPaymentRequestDto;
import com.smart.booking.external.portOne.model.PaymentAnnotationDto;
import com.smart.booking.domain.payment.dto.SavePaymentHistoryDto;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.payment.service.PaymentHistoryService;
import com.smart.booking.domain.payment.service.PaymentService;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Component
public class CancelPaymentFacade {

    private final PaymentService paymentInfoService;
    private final PaymentHistoryService paymentLogService;

    /**
     * 결제 취소 프로세스
     */
    @Transactional
    public void exceuete(com.smart.booking.facade.dto.payment.CancelPaymentRequestDto request, MemberContextDto memberContextDto) {
        //1. 결제 정보 조회
        var payment = paymentInfoService.getPaymentInfo(request.paymentId());

        if (!payment.isRefundable()) {
            throw new CommonException(ResponseCode.NOT_REFUNDABLE_STATUS_ERROR);
        }

        PaymentAnnotationDto cancelResponse;
        var paymentStatus = PaymentStatus.REFUND;
        var cancelAmount = 0;
        var refundableAmount = payment.calculateRefundableAmount(request.reservationDate(), LocalDate.now());
        var responseMessage = paymentStatus.getValue();
        try {

            cancelResponse = paymentInfoService.cancelPayment(
                new CancelPaymentRequestDto(
                    payment.getImpUid(),
                    payment.getMerchantUid(),
                    refundableAmount,
                    "환불 요청"
                )
            );
            //2. 결제 취소 데이터 변경
            cancelAmount = cancelResponse.cancelAmount();
            paymentInfoService.savePaymentCancelInfo(payment);

        } catch (Exception e) {
            //3. 결제 취소 완료 로그 저장
            paymentStatus = PaymentStatus.REFUND_FAIL;
            responseMessage = e.getMessage();
        } finally {
            var historyDto = new SavePaymentHistoryDto(payment, cancelAmount, paymentStatus, responseMessage);
            paymentLogService.savePaymentHistoryLog(historyDto);
        }


    }
}
