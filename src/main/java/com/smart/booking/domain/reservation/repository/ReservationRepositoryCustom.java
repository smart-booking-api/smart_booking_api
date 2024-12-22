package com.smart.booking.domain.reservation.repository;

import com.smart.booking.domain.reservation.entity.Reservation;
import java.util.List;

public interface ReservationRepositoryCustom {
    List<Reservation> getByMemberIdAndYearMonth(String memberId, String yearMonth);
}
