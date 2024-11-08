package com.smart.booking.facade.event.dto;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record CompletePaymentEventDto(
    @NonNull String memberId,
    @NonNull String merchantUid,
    @NonNull String trackingId,
    @NonNull String teeBoxId,
    @NonNull String timeTableId,
    @NonNull Integer amount,
    @NonNull String reservationUserName,
    @NonNull String reservationUserPhoneNum
) {

}