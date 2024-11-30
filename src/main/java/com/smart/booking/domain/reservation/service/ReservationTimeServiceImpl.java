package com.smart.booking.domain.reservation.service;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.reservation.entity.ReservationTimeCode;
import com.smart.booking.domain.reservation.repository.ReservationTimeCodeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationTimeServiceImpl implements ReservationTimeService {
    private final ReservationTimeCodeRepository reservationTimeCodeRepository;

    @Override
    public List<ReservationTimeCode> getReservationTimeBetweenStartAndEnd(String startTimeId, String endTimeId) {
        ReservationTimeCode startTime = reservationTimeCodeRepository.findById(startTimeId).orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_RESERVATION_TIME));
        ReservationTimeCode endTime = reservationTimeCodeRepository.findById(endTimeId).orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_RESERVATION_TIME));
        return reservationTimeCodeRepository.findByTimeBetween(startTime.getTime(), endTime.getTime());
    }
}
