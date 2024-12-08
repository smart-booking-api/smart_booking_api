package com.smart.booking.domain.reservation.dto;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.entity.ReservationMember;
import com.smart.booking.domain.reservation.enums.ReservationStatus;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.facade.dto.reservation.CreatePhoneReservationDto;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class UpsertPhoneReservationDto {
    private Store store;
    private int reservationNo;
    private TeeBox teeBox;
    private LocalDate reservationDate;
    private String startTimeTableId;
    private String endTimeTableId;
    private ReservationMember reservationMember;
    private ReservationStatus reservationStatus;

    public UpsertPhoneReservationDto(CreatePhoneReservationDto createDto, TeeBox teeBox, Member member, int reservationNo) {
        this.reservationNo = reservationNo;
        this.store = teeBox.getStore();
        this.teeBox = teeBox;
        this.reservationDate = LocalDate.parse(createDto.reservationDate());
        this.startTimeTableId = createDto.startTimeTableId();
        this.endTimeTableId = createDto.endTimeTableId();
        this.reservationMember = new ReservationMember(member, createDto.reservationUserName(), createDto.reservationUserPhoneNumber());
        this.reservationStatus = ReservationStatus.RESERVED;
    }

    public Reservation toEntity() {
        return Reservation.builder()
            .store(store)
            .reservationNo(reservationNo)
            .teeBox(teeBox)
            .startTimeId(startTimeTableId)
            .endTimeId(endTimeTableId)
            .reservationMember(reservationMember)
            .reservationStatus(reservationStatus)
            .reservationDate(reservationDate)
            .build();
    }
}
