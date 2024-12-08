package com.smart.booking.domain.reservation.dto;

import lombok.Getter;

@Getter
public class TimeCodeDto {
    private String startTimeId;
    private String endTimeId;

    public TimeCodeDto(String startTimeId, String endTimeId) {
        this.startTimeId = startTimeId;
        this.endTimeId = endTimeId;
    }
}
