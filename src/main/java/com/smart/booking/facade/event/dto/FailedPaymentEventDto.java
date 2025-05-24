package com.smart.booking.facade.event.dto;

import lombok.Builder;
import lombok.NonNull;

// todo 제거
@Builder
public record FailedPaymentEventDto(
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