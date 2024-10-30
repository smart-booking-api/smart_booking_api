package com.smart.booking.domain.reservation.service;

public interface ReservationLockService {


    /**
     * 선점락 생성
     * @param storeId
     * @param timeTableId
     */
    String createReservationLock(String storeId, String timeTableId);
}
