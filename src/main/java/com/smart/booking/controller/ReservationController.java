package com.smart.booking.controller;

import com.smart.booking.domain.reservation.dto.ReservationLockDto;
import com.smart.booking.facade.user.reservation.CreateReservationLockFacade;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class ReservationController {
    private final CreateReservationLockFacade createReservationLockFacade;

    @PostMapping("/lock")
    public void createReservationLock(@NonNull @RequestBody ReservationLockDto.Create createDto) {
        createReservationLockFacade.execute(createDto);
    }
}
