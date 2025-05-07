package com.smart.booking.facade.user.reservation;

import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.entity.ReservationTimeCode;
import com.smart.booking.domain.reservation.service.ReservationTimeService;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.facade.dto.reservation.ReservationSimpleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetReservationByReservationNoFacade {
    private final UserReservationService userReservationService;
    private final ReservationTimeService reservationTimeService;

    public ReservationSimpleResponseDto execute(int reservationNo) {
        Reservation reservation = userReservationService.getReservationByReservationNo(reservationNo);

        return new ReservationSimpleResponseDto(reservation, getTimeByTimeId(reservation.getStartTimeId()), getTimeByTimeId(reservation.getEndTimeId()));
    }

    /**
     * 시간코드 조회
     * @param timeId
     * @return
     */
    private String getTimeByTimeId(String timeId) {
        ReservationTimeCode timeCode = reservationTimeService.getReservationTimeCodeById(timeId);
        return timeCode.getTimeName();
    }
}
