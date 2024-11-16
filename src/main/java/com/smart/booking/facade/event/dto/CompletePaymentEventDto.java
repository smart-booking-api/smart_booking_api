package com.smart.booking.facade.event.dto;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record CompletePaymentEventDto(
    @NonNull String memberId,
    @NonNull String timeTableId,
    @NonNull String trackingId,
    @NonNull String storeId,
    @NonNull String teeBoxId,
    @NonNull Long startTimeTableId,
    @NonNull Long endTimeTableId,
    @NonNull Integer amount,
    String reservationUserName,
    String reservationUserPhoneNumber
) {

}