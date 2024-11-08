package com.smart.booking.facade.dto.payment;

import lombok.NonNull;

public record CompletePaymentRequestDto(
    @NonNull String memberId,
    @NonNull String merchantUid,
    @NonNull String trackingId,
    @NonNull String teeBoxId,
    @NonNull String startTimeTableId,
    @NonNull String endTimeTableId,
    @NonNull Integer amount,
    @NonNull String reservationUserName,
    @NonNull String reservationUserPhoneNum
) {

}