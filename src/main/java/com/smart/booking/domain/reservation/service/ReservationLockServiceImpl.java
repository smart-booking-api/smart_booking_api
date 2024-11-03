package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.reservation.dto.ReservationLockDto;
import com.smart.booking.domain.reservation.entity.ReservationLock;
import com.smart.booking.domain.reservation.repository.ReservationLockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationLockServiceImpl implements ReservationLockService {
    private final ReservationLockRepository reservationLockRepository;

    /**
     * 선점락 생성
     * @param createDto
     */
    @Override
    public void createReservationLock(ReservationLockDto.Create createDto) {
        ReservationLock reservationLock = ReservationLock.builder()
            .key(createDto.getStoreId() + "-" + createDto.getTimeId())
            .memberId(createDto.getMemberId())
            .build();

        reservationLockRepository.save(reservationLock);
    }
}
