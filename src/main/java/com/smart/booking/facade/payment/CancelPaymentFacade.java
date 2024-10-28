package com.smart.booking.facade.payment;

import com.smart.booking.common.util.TransactionEventManager;
import com.smart.booking.domain.common.facade.Facade;
import com.smart.booking.domain.payment.service.FirebasePaymentSyncService;
import com.smart.booking.domain.payment.service.PaymentInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Component
public class CancelPaymentFacade implements Facade<Object, Object> {

    private final PaymentInfoService paymentInfoService;
    private final FirebasePaymentSyncService firebasePaymentSyncService;

    private final TransactionEventManager transactionEventManager;

    /**
     * 결제 취소 프로세스
     * */
    @Override
    @Transactional
    public Object exceuete(Object dto) {
        //1. 결제 정보 조회
        String paymentId = "";
        String teeBoxId = "";
        String timeTableId = "";
        var payment = paymentInfoService.getPaymentInfo(paymentId);

        //2. 결제 취소 로그 저장
        paymentInfoService.savePaymentCancelInfo(paymentId);

        //3. transactionEnd -> firebase sync
        transactionEventManager.onTransactionEnd(() -> {
            firebasePaymentSyncService.removePaymentResultStatus(payment);
        });

        return null;
    }
}
