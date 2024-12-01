package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.store.entity.Store;
import java.time.LocalDate;
import java.util.List;

public interface CommonReservationService {
    List<Reservation> getReservationByStoreAndReservationDate(Store store, LocalDate reservationDate);

    List<Reservation> getReservationByTeeBoxIdAndReservationDate(String teeBoxId, LocalDate searchDate);

    List<Reservation> getReservationByTeeBoxId(String teeBoxId, LocalDate searchDate);

    boolean validateCancelPermission(String reservationId);

    boolean validateSearchPermission(String reservationId);
}
