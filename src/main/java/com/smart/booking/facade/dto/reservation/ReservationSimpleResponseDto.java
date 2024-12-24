package com.smart.booking.facade.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ReservationSimpleResponseDto {
    private String reservationNo;
    private String reservationDate;
    private String startTimeId;
    private String endTimeId;
    private String storeName;
    private String teeBoxNumber;
}
