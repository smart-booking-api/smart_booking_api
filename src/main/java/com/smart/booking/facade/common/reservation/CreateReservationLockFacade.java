package com.smart.booking.facade.common.reservation;

import com.smart.booking.domain.reservation.dto.UpsertReservationLockDto;
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
            UpsertReservationLockDto upsertReservationLockDto = createLockDto(lockDto.teeBoxId(), lockDto.date(), reservationTime, memberId);
            reservationLockService.createReservationLock(upsertReservationLockDto);
        }
    }

    private UpsertReservationLockDto createLockDto(String teeBoxId, String date, ReservationTimeCode timeCode, String memberId) {
        return UpsertReservationLockDto.builder()
            .teeBoxId(teeBoxId)
            .date(date)
            .lockTimeId(timeCode.getId())
            .memberId(memberId)
            .build();
    }
}
