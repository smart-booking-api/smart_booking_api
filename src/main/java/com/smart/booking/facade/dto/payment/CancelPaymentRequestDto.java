package com.smart.booking.facade.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

public record CancelPaymentRequestDto(
    @NonNull String paymentId
) {

}