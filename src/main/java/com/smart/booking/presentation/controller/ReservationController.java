package com.smart.booking.presentation.controller;

import com.smart.booking.common.dto.MemberContext;
import com.smart.booking.domain.reservation.dto.ReservationLockDto;
import com.smart.booking.facade.user.reservation.CreateReservationLockFacade;
import com.smart.booking.facade.user.reservation.DeleteReservationLockFacade;
import com.smart.booking.facade.user.reservation.CheckReservationLockFacade;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/reservation")
public class ReservationController {
    private final CheckReservationLockFacade checkReservationLockFacade;
    private final CreateReservationLockFacade createReservationLockFacade;
    private final DeleteReservationLockFacade deleteReservationLockFacade;

    @GetMapping("/isLocked")
    public boolean checkReservationLock(@RequestParam String storeId, @RequestParam String timeId, MemberContext memberContext) {
        var dto = new ReservationLockDto.Get(storeId, timeId);
        return checkReservationLockFacade.execute(dto, memberContext);
    }

    @PostMapping("/lock")
    public void createReservationLock(@NonNull @RequestBody ReservationLockDto.Create createDto, MemberContext memberContext) {
        createReservationLockFacade.execute(createDto, memberContext);
    }

    @DeleteMapping("/lock")
    public void deleteReservationLock(@NonNull @RequestBody ReservationLockDto.Delete deleteDto) {
        deleteReservationLockFacade.execute(deleteDto);
    }
}
