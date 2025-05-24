package com.smart.booking.domain.reservation.dto;

import lombok.Builder;

@Builder
public record UpsertReservationLockDto(
    String teeBoxId,
    String date,
    String lockTimeId,
    String memberId
) {

    /**
     * 선점락키 생성
     * @return
     */
    public String getKey() {
        return teeBoxId + "-" + date + "-" + lockTimeId;
    }
}
