package com.smart.booking.facade.dto.payment;

import java.time.LocalDate;
import lombok.NonNull;

public record TempCompletePaymentEvent(
    @NonNull String memberId,
    @NonNull String merchantUid,
    @NonNull String trackingId,
    @NonNull String storeId,
    @NonNull String teeBoxId,
    @NonNull LocalDate reservationDate,
    @NonNull String startTimeTableId,
    @NonNull String endTimeTableId,
    @NonNull Integer amount,
    @NonNull String reservationUserName,
    @NonNull String reservationUserPhoneNum
) {

}