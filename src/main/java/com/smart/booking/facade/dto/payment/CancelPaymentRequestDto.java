package com.smart.booking.facade.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.NonNull;

public record CancelPaymentRequestDto(
    @NonNull String paymentId,
    @NonNull LocalDate reservationDate
) {

}