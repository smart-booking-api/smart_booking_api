package com.smart.booking.facade.dto.reservation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PayStatus {
    APPROVAL("승인");

    private final String name;
}
