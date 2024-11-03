package com.smart.booking.domain.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ReservationLockDto {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Create {
        private String storeId;
        private String timeId;
        private String memberId;
    }
}
