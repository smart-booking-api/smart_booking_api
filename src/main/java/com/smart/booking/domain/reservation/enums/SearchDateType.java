package com.smart.booking.domain.reservation.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SearchDateType {
    PAY_DATE("결제일"),
    RESERVATION_DATE("예약일");

    private final String name;
}
