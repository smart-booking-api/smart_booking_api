package com.smart.booking.facade.user.reservation;

import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.service.ReservationTimeService;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.facade.dto.reservation.ReservationSimpleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetReservationFacadeByReservationNo {
    private final UserReservationService userReservationService;
    private final ReservationTimeService reservationTimeService;

    public ReservationSimpleResponseDto execute(int reservationNo) {
        Reservation reservation = userReservationService.getReservationByReservationNo(reservationNo);

        return new ReservationSimpleResponseDto(reservation, reservationTimeService);
    }
}
