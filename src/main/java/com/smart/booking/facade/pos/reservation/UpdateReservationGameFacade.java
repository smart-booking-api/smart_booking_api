package com.smart.booking.facade.pos.reservation;

import com.smart.booking.domain.reservation.enums.ReservationStatus;
import com.smart.booking.domain.reservation.service.UserReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateReservationGameFacade {
    private final UserReservationService userReservationService;

    public void execute(int reservationNo) {
        userReservationService.updateReservationStatus(reservationNo, ReservationStatus.COMPLETED);

        // todo 게임시작 및 상태변경
    }
}
