package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.reservation.dto.ReservationDateHistory;
import com.smart.booking.domain.reservation.dto.ReservationSimpleResponse;
import java.util.List;

public class UserReservationServiceImpl implements UserReservationService {

    @Override
    public List<ReservationSimpleResponse> getMyReservations(String userId, String startDate) {
        return null;
    }

    @Override
    public void getEnableTeeBoxes(String storeId, String reserveDate) {

    }

    @Override
    public void getEnableTimes(String storeId, String reserveDate, String teeBoxId) {

    }

    @Override
    public boolean isOccupiedReservation(String storeId, String reserveDate, String teeBoxId, String timeCodeId) {
        return false;
    }

    @Override
    public void createReservation(String storeId, String reserveDate, String teeBoxId, String startTimeCodeId, String endTimeCodeId) {

    }

    @Override
    public List<ReservationDateHistory> getMyReservationDateHistory(String userId, String yearMonth) {
        return null;
    }

    @Override
    public void startReservationStatus(String reservationId) {

    }
}
