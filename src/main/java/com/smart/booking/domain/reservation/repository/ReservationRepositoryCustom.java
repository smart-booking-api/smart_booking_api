package com.smart.booking.domain.reservation.repository;

import com.smart.booking.domain.reservation.entity.Reservation;
import java.time.LocalDate;
import java.util.List;

public interface ReservationRepositoryCustom {
    List<Reservation> getReservationByTeeBoxIdAndReservationDate(String teeBoxId, LocalDate reservationDate);
}
