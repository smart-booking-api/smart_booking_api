package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.reservation.dto.ReservationLockDto;

public interface ReservationLockService {

    /**
     * 선점락 생성(key: storeId-timeId)
     * @param createDto
     */
    void createReservationLock(ReservationLockDto.Create createDto);
}
