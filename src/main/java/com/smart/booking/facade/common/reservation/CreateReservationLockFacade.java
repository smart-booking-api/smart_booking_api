package com.smart.booking.facade.common.reservation;

import com.smart.booking.common.dto.MemberContext;
import com.smart.booking.domain.reservation.dto.ReservationLockDto;
import com.smart.booking.domain.reservation.entity.ReservationTimeCode;
import com.smart.booking.domain.reservation.service.ReservationLockService;
import com.smart.booking.domain.reservation.service.ReservationTimeService;
import com.smart.booking.facade.dto.reservation.CreateReservationLockDto;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateReservationLockFacade {
    private final ReservationLockService reservationLockService;
    private final ReservationTimeService reservationTimeService;

    @Transactional
    public void execute(@NonNull CreateReservationLockDto lockDto, String memberId) {
        List<ReservationTimeCode> reservationTimeCodes = reservationTimeService.getReservationTimeBetweenStartAndEnd(lockDto.startTimeId(), lockDto.endTimeId());

        for (ReservationTimeCode reservationTime : reservationTimeCodes) {
            ReservationLockDto reservationLockDto = createLockDto(lockDto.teeBoxId(), reservationTime, memberId);
            reservationLockService.createReservationLock(reservationLockDto);
        }
    }

    private ReservationLockDto createLockDto(String teeBoxId, ReservationTimeCode timeCode, String memberId) {
        return ReservationLockDto.builder()
            .teeBoxId(teeBoxId)
            .lockTimeId(timeCode.getId())
            .memberId(memberId)
            .build();
    }
}
