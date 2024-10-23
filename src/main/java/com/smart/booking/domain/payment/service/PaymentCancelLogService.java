package com.smart.booking.domain.payment.service;

public interface PaymentCancelLogService {
    /**
     * 결제 취소 요청 로그 저장
     * request :
     * memberId,
     * tBox Id,
     * timeTable id,
     * firebase 상태
     * */
    void saveCancelLog(Object cancelLog);
}
