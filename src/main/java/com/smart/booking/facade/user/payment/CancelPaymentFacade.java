package com.smart.booking.facade.user.payment;

import com.smart.booking.common.dto.MemberContext;
import com.smart.booking.domain.external.dto.CancelPaymentRequestDto;
import com.smart.booking.domain.payment.dto.SavePaymentHistoryDto;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.payment.service.PaymentHistoryService;
import com.smart.booking.domain.payment.service.PaymentInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Component
public class CancelPaymentFacade {

    private final PaymentInfoService paymentInfoService;
    private final PaymentHistoryService paymentLogService;

    /**
     * 결제 취소 프로세스
     */
    @Transactional
    public void exceuete(String paymentId, MemberContext memberContext) {
        //1. 결제 정보 조회
        var payment = paymentInfoService.getPaymentInfo(paymentId);
        var cancelResponse = paymentInfoService.cancelPayment(
            new CancelPaymentRequestDto(
                payment.getImpUid(),
                payment.getMerchantUid(),
                payment.getSupplyAmount().intValue(),
                "환불 요청"
            )
        );

        //2. 결제 취소 데이터 변경
        paymentInfoService.savePaymentCancelInfo(payment);

        //3. 결제 취소 완료 로그 저장
        var historyDto = new SavePaymentHistoryDto(payment, cancelResponse.cancelAmount(), PaymentStatus.REFUND);
        paymentLogService.savePaymentHistoryLog(historyDto);

    }
}
