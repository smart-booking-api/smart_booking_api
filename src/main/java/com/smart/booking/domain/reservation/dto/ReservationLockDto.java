package com.smart.booking.domain.reservation.dto;

import lombok.Builder;

@Builder
public record ReservationLockDto(
    String teeBoxId,
    String lockTimeId,
    String memberId
) {}
