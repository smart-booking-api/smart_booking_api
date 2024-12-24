package com.smart.booking.facade.common.reservation;

import com.smart.booking.common.firebase.FirebaseComponent;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.reservation.dto.UpsertReservationLockDto;
import com.smart.booking.domain.reservation.entity.ReservationTimeCode;
import com.smart.booking.domain.reservation.service.ReservationLockService;
import com.smart.booking.domain.reservation.service.ReservationTimeService;
import com.smart.booking.facade.dto.reservation.CreateReservationLockDto;
import com.smart.booking.facade.dto.reservation.ReservationFirebaseStatusDto;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateReservationLockFacade {
    private final ReservationLockService reservationLockService;
    private final ReservationTimeService reservationTimeService;
    private final FirebaseComponent firebaseComponent;
    private static final String COLLECTION_NAME = "booking";

    @Transactional
    public void execute(@NonNull CreateReservationLockDto lockDto, String memberId) throws Exception {
        // 시간별 선점락 생성
        createTimesLock(lockDto, memberId);

        // firebase data 생성
        firebaseComponent.updateDocument(COLLECTION_NAME, lockDto.trackingId(),
            new ReservationFirebaseStatusDto(lockDto.trackingId(), memberId, PaymentStatus.PENDING));
    }

    private void createTimesLock(@NotNull CreateReservationLockDto lockDto, String memberId) {
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
