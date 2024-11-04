package com.smart.booking.domain.reservation.service;

import com.smart.booking.common.dto.MemberContext;
import com.smart.booking.domain.reservation.dto.ReservationLockDto;
import com.smart.booking.domain.reservation.entity.ReservationLock;

public interface ReservationLockService {

    /**
     * 선점락 생성(key: storeId-timeId)
     * @param createDto
     */
    void createReservationLock(ReservationLockDto.Create createDto, MemberContext memberContext);

    /**
     * 선점락 삭제
     * @param deleteDto
     */
    void deleteReservationLock(ReservationLockDto.Delete deleteDto);

    /**
     * 선점락 조회
     * @param getDto
     * @return
     */
    ReservationLock getReservationLock(ReservationLockDto.Get getDto);
}
