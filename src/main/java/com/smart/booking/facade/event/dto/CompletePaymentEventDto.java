package com.smart.booking.facade.event.dto;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record CompletePaymentEventDto(
    @NonNull String memberId,
    @NonNull String trackingId,
    @NonNull String teeBoxId,
    @NonNull String startTimeTableId,
    @NonNull String endTimeTableId,
    @NonNull Integer amount,
    String reservationUserName,
    String reservationUserPhoneNumber
) {

}