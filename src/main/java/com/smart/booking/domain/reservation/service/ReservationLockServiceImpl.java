package com.smart.booking.domain.reservation.service;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.reservation.dto.ReservationLockDto;
import com.smart.booking.domain.reservation.entity.ReservationLock;
import com.smart.booking.domain.reservation.repository.ReservationLockRepository;
import com.smart.booking.facade.dto.reservation.CreateReservationLockDto;
import java.util.Objects;
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
     * @param lockDto
     */
    @Override
    public ReservationLock createReservationLock(ReservationLockDto lockDto) {
        validateReservationLock(lockDto);

        ReservationLock reservationLock = ReservationLock.builder()
            .key(getKey(lockDto))
            .memberId(lockDto.memberId())
            .build();

        return reservationLockRepository.save(reservationLock);
    }

    @Override
    public void deleteReservationLock(CreateReservationLockDto deleteDto) {
//        ReservationLock reservationLock = ReservationLock.builder()
//            .key(deleteDto.getStoreId() + "-" + deleteDto.getTimeId())
//            .build();
//        reservationLockRepository.delete(reservationLock);
    }

    private void validateReservationLock(ReservationLockDto lockDto) {
        ReservationLock reservationLock = getReservationLock(getKey(lockDto));

        if (!Objects.isNull(reservationLock))
            throw new CommonException(ResponseCode.ALREADY_LOCK_RESERVATION);
    }

    private ReservationLock getReservationLock(String key) {
        log.info("key::" + key);
        return reservationLockRepository.findById(key).orElse(null);
    }

    private String getKey(ReservationLockDto lockDto) {
        return lockDto.teeBoxId() + "-" + lockDto.lockTimeId();
    }
}
