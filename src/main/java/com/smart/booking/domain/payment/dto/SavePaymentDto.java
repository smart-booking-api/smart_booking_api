package com.smart.booking.domain.payment.dto;

import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import lombok.Getter;
import lombok.NonNull;

public record SavePaymentDto (
    @NonNull Integer totalAmount,
    @NonNull PaymentStatus paymentStatus,

    @NonNull TeeBox teeBox
    ){

}