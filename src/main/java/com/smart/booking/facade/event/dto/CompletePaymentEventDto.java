package com.smart.booking.facade.event.dto;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record CompletePaymentEventDto(
    @NonNull String memberId,
    @NonNull String merchantUid,
    @NonNull String trackingId,
    @NonNull String storeId,
    @NonNull String teeBoxId,
    @NonNull String startTimeTableId,
    @NonNull String endTimeTableId,
    @NonNull Integer amount,
    @NonNull String reservationUserName,
    @NonNull String reservationUserPhoneNum
) {

}