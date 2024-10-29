package com.smart.booking.facade.dto.payment;

import lombok.NonNull;

public record SavePaymentTrackingHistoryRequestDto(
    @NonNull String trackingId,
    @NonNull String teeBoxId,
    @NonNull String timeTableId,
    @NonNull Integer amount
) {

}