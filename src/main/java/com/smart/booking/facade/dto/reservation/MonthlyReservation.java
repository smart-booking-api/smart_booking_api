package com.smart.booking.facade.dto.reservation;

import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.enums.ReservationStatus;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class MonthlyReservation {
    private ReservationStatus reservationStatus;
    private LocalDate reservationDate;

    public MonthlyReservation(Reservation reservation) {
        this.reservationStatus = reservation.getReservationStatus();
        this.reservationDate = reservation.getReservationDate();
    }
}
