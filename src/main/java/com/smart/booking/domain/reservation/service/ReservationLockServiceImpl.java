package com.smart.booking.domain.reservation.service;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.reservation.dto.UpsertReservationLockDto;
import com.smart.booking.domain.reservation.entity.ReservationLock;
import com.smart.booking.domain.reservation.repository.ReservationLockRepository;
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
    public ReservationLock createReservationLock(UpsertReservationLockDto lockDto) {
        // 이미 선점이 걸려있는지 체크
        validateReservationLock(lockDto);

        ReservationLock reservationLock = ReservationLock.builder()
            .key(getKey(lockDto))
            .memberId(lockDto.memberId())
            .build();

        return reservationLockRepository.save(reservationLock);
    }

    @Override
    public void deleteReservationLock(UpsertReservationLockDto deleteDto) {
        // 내 선점락인지 체크
        validateMyReservationLock(deleteDto);

        ReservationLock reservationLock = ReservationLock.builder()
            .key(getKey(deleteDto))
            .memberId(deleteDto.memberId())
            .build();

        reservationLockRepository.delete(reservationLock);
    }

    @Override
    public ReservationLock getReservationLock(UpsertReservationLockDto upsertReservationLockDto) {
        return getReservationLock(getKey(upsertReservationLockDto));
    }

    /**
     * 선점락이 있는지 체크
     * @param lockDto
     */
    private void validateReservationLock(UpsertReservationLockDto lockDto) {
        ReservationLock reservationLock = getReservationLock(getKey(lockDto));

        if (!Objects.isNull(reservationLock))
            throw new CommonException(ResponseCode.ALREADY_LOCK_RESERVATION);
    }

    /**
     * 내 선점락인지 체크
     * @param lockDto
     */
    private void validateMyReservationLock(UpsertReservationLockDto lockDto) {
        ReservationLock reservationLock = getReservationLock(getKey(lockDto));

        if (!reservationLock.getMemberId().equals(lockDto.memberId())) {
            throw new CommonException(ResponseCode.NOT_MY_RESERVATION_LOCK);
        }
    }

    /**
     * key 로 선점락 조회
      * @param key
     * @return
     */
    private ReservationLock getReservationLock(String key) {
        log.info("key::" + key);
        return reservationLockRepository.findById(key).orElse(null);
    }

    /**
     * 선점락 키 생성
     * @param lockDto
     * @return
     */
    private String getKey(UpsertReservationLockDto lockDto) {
        return lockDto.teeBoxId() + "-" + lockDto.date() + "-" +lockDto.lockTimeId();
    }
}
