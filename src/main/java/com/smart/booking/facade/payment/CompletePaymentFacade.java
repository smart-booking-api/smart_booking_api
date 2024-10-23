package com.smart.booking.facade.payment;

import com.smart.booking.common.util.TransactionEventManager;
import com.smart.booking.domain.common.facade.Facade;
import com.smart.booking.domain.payment.service.FirebasePaymentSyncService;
import com.smart.booking.domain.payment.service.PaymentInfoService;
import com.smart.booking.domain.payment.service.PaymentLogService;
import com.smart.booking.domain.payment.service.PaymentTrackingInfoService;
import com.smart.booking.domain.payment.service.PaymentTrackingInfoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Component
public class CompletePaymentFacade implements Facade<Object, Object> {

    private final PaymentInfoService paymentInfoService;
    private final PaymentTrackingInfoService paymentTrackingInfoService;
    private final PaymentLogService paymentLogService;
    private final FirebasePaymentSyncService firebasePaymentSyncService;

    private final TransactionEventManager transactionEventManager;

    /**
     * 결제 완료 프로세스
     * */

    @Override
    @Transactional
    public Object exceuete(Object dto) {
        //1. 결제 완료 정보 저장
        String trackingId = "";
        String teeBoxId = "";
        String timeTableId = "";
        var payment = paymentInfoService.savePaymentCompleteInfo(trackingId, teeBoxId, timeTableId);
        String paymentId = "";

        //2. 결제-트랙킹 정보 업데이트
        paymentTrackingInfoService.matchPaymentAndTrackingInfo(paymentId, trackingId);

        //3. 결제 완료 로그 저장
        paymentLogService.savePaymentCancelRequestLog(paymentId);

        //4. transactionEnd -> firebase sync
        transactionEventManager.onTransactionEnd(() ->{
            firebasePaymentSyncService.syncPaymentResultStatus(payment);
        });
        return null;
    }
}
