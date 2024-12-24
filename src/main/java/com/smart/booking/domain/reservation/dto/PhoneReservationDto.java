package com.smart.booking.domain.reservation.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PhoneReservationDto {
    private int reservationNo;
    private String reservationName;
    private LocalDate reservationDate;
    private String reservationTime;
    private String storeNameNo;

    public PhoneReservationDto(int reservationNo, String reservationName, LocalDate reservationDate, String startTime, String endTime,
        String storeName, Integer teeBoxNo) {
        this.reservationNo = reservationNo;
        this.reservationName = reservationName;
        this.reservationDate = reservationDate;
        this.reservationTime = startTime + "~" + endTime;
        this.storeNameNo = storeName + "-" +teeBoxNo;
    }
}
