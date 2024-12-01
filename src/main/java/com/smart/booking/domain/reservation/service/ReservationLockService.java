package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.reservation.dto.ReservationLockDto;
import com.smart.booking.domain.reservation.entity.ReservationLock;

public interface ReservationLockService {

    /**
     * 선점락 생성
     * @param createDto
     */
    ReservationLock createReservationLock(ReservationLockDto createDto);

    /**
     * 선점락 삭제
     * @param deleteDto
     */
    void deleteReservationLock(ReservationLockDto deleteDto);

    /**
     * 선점락 조회
     * @param searchDto
     * @return
     */
    ReservationLock getReservationLock(ReservationLockDto searchDto);
}
