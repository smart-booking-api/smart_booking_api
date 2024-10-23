package com.smart.booking.domain.payment.entity;

public enum PaymentStatus {
    PENDING("결제 대기 중"),
    COMPLETE("결제 완료"),
    REFUND("환불"),
    CANCEL("결제 취소");

    PaymentStatus(String value) {
    }
}