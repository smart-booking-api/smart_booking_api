package com.smart.booking.domain.reservation.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReservationStatus {
    RESERVED("예약완료"),
    COMPLETED("이용완료"),
    CANCELED("취소");

    private final String name;
}
