package com.smart.booking.domain.payment.dto;

import com.smart.booking.domain.payment.entity.Payment;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.NonNull;
import org.hibernate.annotations.NotFound;

@Builder
public record SavePaymentTrackingHistoryDto(

    @NonNull String memberId,
    @NonNull String trackingId,
    @NonNull String teeBoxId,
    @NonNull String timeTableId,
    @NonNull Integer totalAmount,
    @NonNull PaymentStatus paymentStatus
) {

}