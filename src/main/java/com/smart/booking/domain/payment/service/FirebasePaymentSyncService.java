package com.smart.booking.domain.payment.service;

public interface FirebasePaymentSyncService {
    /**
     * firebase 결제 결과 sync
     * request :
     * memberId,
     * tBox Id,
     * timeTable id,
     * firebase 상태
     * */
    void syncPaymentResultStatus(Object result);


    /**
     * firebase 결제 결과 삭제
     * */
    void removePaymentResultStatus(Object result);
}
