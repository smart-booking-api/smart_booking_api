package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.reservation.dto.TimeCodeDto;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CommonReservationService {
    Optional<Reservation> findByReservationNo(int reservationNo);

    void cancelReservation(String reservationId, String memberId, MemberType memberType);

    List<Reservation> getReservationByTeeBoxAndDateAndTimeIds(TeeBox teeBox, LocalDate reservationDate, TimeCodeDto timeCodeDto);

    List<Reservation> getReservationByTeeBoxAndReservationDate(TeeBox teeBox, LocalDate searchDate);

    boolean validateCancelPermission(String reservationId, String memberId, MemberType memberType);

    boolean validateSearchPermission(String reservationId, String memberId);

    Reservation getReservationByTrackingId(String trackingId);
}
