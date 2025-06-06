package com.smart.booking.domain.reservation.dto;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.entity.ReservationMember;
import com.smart.booking.domain.reservation.enums.ReservationStatus;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record UpsertReservationDto(
    Store store,
    TeeBox teeBox,
    LocalDate reservationDate,
    @NonNull String startTimeTableId,
    @NonNull String endTimeTableId,
    ReservationStatus reservationStatus,
    Member member,
    @NonNull String reservationUserName,
    @NonNull String reservationUserPhoneNumber,
    String trackingId,
    BigDecimal onSiteFee
) {
    public Reservation toEntity(int randomNumber) {
        ReservationMember reservationMember = new ReservationMember(member, reservationUserName, reservationUserPhoneNumber);
        return Reservation.builder()
            .store(store)
            .teeBox(teeBox)
            .reservationNo(randomNumber)
            .reservationDate(reservationDate)
            .startTimeId(startTimeTableId)
            .endTimeId(endTimeTableId)
            .reservationStatus(ReservationStatus.RESERVED)
            .reservationMember(reservationMember)
            .trackingId(trackingId)
            .onSiteFee(onSiteFee)
            .build();
    }
}
