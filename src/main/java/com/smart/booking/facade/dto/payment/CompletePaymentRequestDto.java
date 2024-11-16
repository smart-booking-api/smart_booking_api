package com.smart.booking.facade.dto.payment;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record CompletePaymentRequestDto(
    @NonNull String memberId,
    @NonNull String merchantUid,
    @NonNull String trackingId,
    @NonNull String teeBoxId,
    @NonNull Long startTimeTableId,
    @NonNull Long endTimeTableId,
    @NonNull Integer amount,
    @NonNull String reservationUserName,
    @NonNull String reservationUserPhoneNum
) {

}