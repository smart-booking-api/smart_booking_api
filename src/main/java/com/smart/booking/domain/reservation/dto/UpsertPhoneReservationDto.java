package com.smart.booking.domain.reservation.dto;

import com.smart.booking.facade.dto.reservation.CreatePhoneReservationDto;
import lombok.Getter;

@Getter
public class UpsertPhoneReservationDto {
    private String teeBoxId;
    private String reservationDate;
    private  String startTimeTableId;
    private  String endTimeTableId;
    private String reservationUserName;
    private String reservationUserPhoneNumber;
    private String memberId;

    public UpsertPhoneReservationDto(CreatePhoneReservationDto createPhoneReservationDto, String memberId) {
        this.teeBoxId = createPhoneReservationDto.teeBoxId();
        this.reservationDate = createPhoneReservationDto.reservationDate();
        this.startTimeTableId = createPhoneReservationDto.startTimeTableId();
        this.endTimeTableId = createPhoneReservationDto.endTimeTableId();
        this.reservationUserName = createPhoneReservationDto.reservationUserName();
        this.reservationUserPhoneNumber = createPhoneReservationDto.reservationUserPhoneNumber();
        this.memberId = memberId;
    }
}
