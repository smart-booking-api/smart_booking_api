package com.smart.booking.facade.user.reservation;

import com.smart.booking.domain.reservation.dto.ReservationLockDto;
import com.smart.booking.domain.reservation.service.ReservationLockService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateReservationLockFacade {
    private final ReservationLockService reservationLockService;

    public void execute(@NonNull ReservationLockDto.Create createDto) {
        reservationLockService.createReservationLock(createDto);
    }
}
