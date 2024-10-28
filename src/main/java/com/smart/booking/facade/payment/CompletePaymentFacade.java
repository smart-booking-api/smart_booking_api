package com.smart.booking.facade.payment;

import com.smart.booking.common.exception.CommonException;
import com.smart.booking.common.util.TransactionEventManager;
import com.smart.booking.controller.dto.CompletePaymentRequestDto;
import com.smart.booking.domain.common.facade.Facade;
import com.smart.booking.domain.payment.dto.SavePaymentDto;
import com.smart.booking.domain.payment.dto.SavePaymentHistoryDto;
import com.smart.booking.domain.payment.entity.PaymentApiHistory;
import com.smart.booking.domain.payment.entity.PaymentHistory;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.payment.service.FirebasePaymentSyncService;
import com.smart.booking.domain.payment.service.PaymentInfoService;
import com.smart.booking.domain.payment.service.PaymentHistoryService;
import com.smart.booking.domain.payment.service.PaymentTrackingInfoService;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Component
public class CompletePaymentFacade implements Facade<CompletePaymentRequestDto, Void> {

    private final PaymentInfoService paymentInfoService;
    private final PaymentTrackingInfoService paymentTrackingInfoService;
    private final PaymentHistoryService paymentLogService;
    private final FirebasePaymentSyncService firebasePaymentSyncService;

    private final TransactionEventManager transactionEventManager;

    /**
     * 결제 완료 프로세스
     *
     * @return
     */

    @Override
    @Transactional
    public Void exceuete(CompletePaymentRequestDto dto) throws CommonException {
        //1. 결제 완료 정보 저장
        //TODO teeBox service에 id로 조회 요청
        TeeBox teeBox = null;

        var savePaymentDto = new SavePaymentDto(dto.amount(), PaymentStatus.COMPLETE, teeBox);
        var payment = paymentInfoService.savePaymentCompleteInfo(savePaymentDto);

        //2. 결제-트랙킹 정보 업데이트
        paymentTrackingInfoService.matchPaymentAndTrackingInfo(payment.getPaymentId(), dto.trackingId());

        //3. 결제 완료 로그 저장
        var historyDto = new SavePaymentHistoryDto(payment,payment.getTotalAmount(), payment.getPaymentStatus());
        paymentLogService.savePaymentCompleteRequestLog(historyDto);

        //4. transactionEnd -> firebase sync
        transactionEventManager.onTransactionEnd(() ->{
            firebasePaymentSyncService.syncPaymentResultStatus(payment);
        });
        return null;
    }
}
