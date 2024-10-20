package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.reservation.dto.ReservationDetailResponse;

public abstract class CommonReservationService {
    /**
     * 예약 취소
     * @param reservationId
     */
    public void cancelReservation(String reservationId) {
        if (validateCancelPermission(reservationId)) {
            // todo 예약취소 로직
            // admin : 관리자체크 , user: 자기 예약인지 체크
        }
    }

    /**
     * 예약 상세조회
     * @param reservationId
     * @return
     */
    public ReservationDetailResponse getReservationDetail(String reservationId) {
        // todo 예약 조회
        if (validateSearchPermission(reservationId)) {
            // admin : 관리자체크 , user: 자기 예약인지 체크
        }
        return null;
    }

    protected abstract boolean validateCancelPermission(String reservationId);

    protected abstract boolean validateSearchPermission(String reservationId);
}
