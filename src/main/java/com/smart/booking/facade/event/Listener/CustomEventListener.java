package com.smart.booking.facade.event.Listener;

import com.smart.booking.domain.payment.service.PaymentInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

//이벤트 발행 시 실행
@Slf4j
@RequiredArgsConstructor
@Component
public class CustomEventListener {

    //TODO 예약 서비스에 예약 완료 요청 이벤트 전송로직 추가
    private final PaymentInfoService paymentInfoService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handler(String paymentId) {
        var test = paymentInfoService.getPaymentInfo(paymentId);
        log.info(paymentId + "::: event 실행 테스트");
    }
}
