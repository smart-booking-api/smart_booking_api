package com.smart.booking.domain.reservation.dto;

import lombok.Builder;

@Builder
public record ReservationLockDto(
    String teeBoxId,
    String date,
    String lockTimeId,
    String memberId
) {}
