package com.smart.booking.domain.payment.dto;

import com.smart.booking.domain.payment.entity.Payment;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NonNull;

public record SavePaymentHistoryDto(
    @NonNull Payment payment,
    @NonNull Integer totalAmount,

    @NonNull PaymentStatus paymentStatus
) {

}