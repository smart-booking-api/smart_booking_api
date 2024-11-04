package com.smart.booking.facade.user.reservation;

import com.smart.booking.common.dto.MemberContext;
import com.smart.booking.domain.reservation.dto.ReservationLockDto;
import com.smart.booking.domain.reservation.entity.ReservationLock;
import com.smart.booking.domain.reservation.service.ReservationLockService;
import java.util.Objects;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckReservationLockFacade {
    private final ReservationLockService reservationLockService;

    public boolean execute(@NonNull ReservationLockDto.Get getDto, MemberContext memberContext) {
        ReservationLock reservationLock = reservationLockService.getReservationLock(getDto);
        if (Objects.isNull(reservationLock) || reservationLock.getMemberId().equals(memberContext.getMemberId())) {
            return false;
        }
        return true;
    }
}
