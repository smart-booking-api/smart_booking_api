package com.smart.booking.facade.event.dto;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record FailPaymentEventDto(
    @NonNull String paymentId,
    @NonNull String memberId,
    @NonNull String trackingId,
    @NonNull String teeBoxId,
    @NonNull String startTimeTableId,
    @NonNull String endTimeTableId,
    String failReason,
    int failedAt
) {

}