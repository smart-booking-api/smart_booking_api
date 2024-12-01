package com.smart.booking.domain.reservation.repository;

import com.smart.booking.domain.reservation.entity.ReservationTimeCode;
import java.sql.Time;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationTimeCodeRepository extends JpaRepository<ReservationTimeCode, String> {
    List<ReservationTimeCode> findByTimeBetween(Time startTime, Time endTime);

    ReservationTimeCode findByTimeName(String timeName);
}
