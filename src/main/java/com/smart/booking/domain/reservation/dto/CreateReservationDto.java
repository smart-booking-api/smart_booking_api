package com.smart.booking.domain.reservation.dto;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record CreateReservationDto(
    @NonNull String storeId,
    @NonNull String teeBoxId,
    @NonNull String startTimeTableId,
    @NonNull String endTimeTableId,
    @NonNull String memberId,
    @NonNull String reservationUserName,
    @NonNull String reservationUserPhoneNumber
) {
}
