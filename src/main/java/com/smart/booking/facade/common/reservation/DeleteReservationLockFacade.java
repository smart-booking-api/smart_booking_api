package com.smart.booking.facade.common.reservation;

import com.smart.booking.facade.dto.reservation.CreateReservationLockDto;
import com.smart.booking.domain.reservation.service.ReservationLockService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteReservationLockFacade {
    private final ReservationLockService reservationLockService;

    public void execute(@NonNull CreateReservationLockDto deleteDto) {
        reservationLockService.deleteReservationLock(deleteDto);
    }
}
