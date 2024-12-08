package com.smart.booking.domain.reservation.dto;

import lombok.Builder;

@Builder
public record UpsertReservationLockDto(
    String teeBoxId,
    String date,
    String lockTimeId,
    String memberId
) {}
