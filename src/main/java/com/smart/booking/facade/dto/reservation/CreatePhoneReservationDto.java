package com.smart.booking.facade.dto.reservation;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record CreatePhoneReservationDto(
    String teeBoxId,
    String reservationDate,
    @NonNull String startTimeTableId,
    @NonNull String endTimeTableId,
    @NonNull String reservationUserName,
    @NonNull String reservationUserPhoneNumber
) {
}
