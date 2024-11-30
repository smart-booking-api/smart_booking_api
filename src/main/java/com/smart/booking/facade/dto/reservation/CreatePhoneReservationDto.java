package com.smart.booking.facade.dto.reservation;

import java.time.LocalDate;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record CreatePhoneReservationDto(
    String teeBoxId,
    LocalDate reservationDate,
    @NonNull String startTimeTableId,
    @NonNull String endTimeTableId,
    @NonNull String reservationUserName,
    @NonNull String reservationUserPhoneNumber
) {
}
