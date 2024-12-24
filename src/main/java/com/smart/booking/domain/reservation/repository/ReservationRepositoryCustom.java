package com.smart.booking.domain.reservation.repository;

import com.smart.booking.domain.reservation.dto.PhoneReservationDto;
import com.smart.booking.domain.reservation.entity.Reservation;
import java.util.List;

public interface ReservationRepositoryCustom {
    List<Reservation> findByMemberIdAndYearMonth(String memberId, String yearMonth);
    List<PhoneReservationDto> findBySearchTextAndMemberId(String searchText, String memberId);
}
