package com.smart.booking.facade.common.reservation;

import com.smart.booking.domain.reservation.dto.UpsertReservationLockDto;
import com.smart.booking.domain.reservation.entity.ReservationTimeCode;
import com.smart.booking.domain.reservation.service.ReservationTimeService;
import com.smart.booking.facade.dto.reservation.CreateReservationLockDto;
import com.smart.booking.domain.reservation.service.ReservationLockService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteReservationLockFacade {
    private final ReservationLockService reservationLockService;
    private final ReservationTimeService reservationTimeService;

    public void execute(@NonNull CreateReservationLockDto deleteDto, String memberId) {
        List<ReservationTimeCode> reservationTimeCodes = reservationTimeService.getReservationTimeBetweenStartAndEnd(deleteDto.startTimeId(), deleteDto.endTimeId());

        for (ReservationTimeCode reservationTime : reservationTimeCodes) {
            UpsertReservationLockDto upsertReservationLockDto = createLockDto(deleteDto.teeBoxId(), reservationTime, memberId);
            reservationLockService.deleteReservationLock(upsertReservationLockDto);
        }
    }

    private UpsertReservationLockDto createLockDto(String teeBoxId, ReservationTimeCode timeCode, String memberId) {
        return UpsertReservationLockDto.builder()
            .teeBoxId(teeBoxId)
            .lockTimeId(timeCode.getId())
            .memberId(memberId)
            .build();
    }
}
