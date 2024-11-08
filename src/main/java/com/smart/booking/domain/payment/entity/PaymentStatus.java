package com.smart.booking.domain.payment.entity;

import com.smart.booking.common.enums.EnumModel;
import lombok.NonNull;

public enum PaymentStatus implements EnumModel {
    PENDING("결제 대기 중", "ready"),
    COMPLETE("결제 완료", "paid"),
    REFUND("환불", "cancelled"),
    CANCEL("결제 실패", "failed");


    private final String value;
    private final String externalValue;

    PaymentStatus(String value, String externalValue) {
        this.value = value;
        this.externalValue = externalValue;
    }

    @Override
    public @NonNull String getKey() {
        return name();
    }

    @Override
    public @NonNull String getValue() {
        return this.value;
    }

    public @NonNull String getExternalValue() {
        return this.externalValue;
    }
}