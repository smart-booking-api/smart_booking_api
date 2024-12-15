package com.smart.booking.facade.event.dto;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record RefundPaymentEventDto(
    @NonNull String memberId,
    @NonNull String reservationId,
    @NonNull String trackingId
) {

}