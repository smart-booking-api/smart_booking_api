package com.smart.booking.domain.reservation.service;

import com.smart.booking.common.dto.MemberContext;
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
    public void createReservationLock(ReservationLockDto.Create createDto, MemberContext memberContext) {
        ReservationLock reservationLock = ReservationLock.builder()
            .key(createDto.getStoreId() + "-" + createDto.getTimeId())
            .memberId(memberContext.getMemberId())
            .build();

        reservationLockRepository.save(reservationLock);
    }

    @Override
    public void deleteReservationLock(ReservationLockDto.Delete deleteDto) {
        ReservationLock reservationLock = ReservationLock.builder()
            .key(deleteDto.getStoreId() + "-" + deleteDto.getTimeId())
            .build();
        reservationLockRepository.delete(reservationLock);
    }

    @Override
    public ReservationLock getReservationLock(ReservationLockDto.Get getDto) {
        log.info("key::" + getDto.getKey());
        return reservationLockRepository.findById(getDto.getKey()).orElse(null);
    }
}
