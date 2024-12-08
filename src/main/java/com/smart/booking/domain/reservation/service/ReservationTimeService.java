package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.reservation.entity.ReservationTimeCode;
import java.util.List;

public interface ReservationTimeService {
    ReservationTimeCode getReservationTimeCodeById(String timeId);

    List<ReservationTimeCode> getReservationTimeBetweenStartAndEnd(String startTimeId, String endTimeId);

    ReservationTimeCode getReservationTimeByTimeName(String timeName);
}
