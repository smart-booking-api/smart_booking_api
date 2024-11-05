package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.reservation.dto.CreateReservationDto;
import com.smart.booking.domain.reservation.dto.ReservationDateHistory;
import com.smart.booking.domain.reservation.dto.ReservationSimpleResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserReservationServiceImpl extends CommonReservationService implements UserReservationService {

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
    public void createReservation(CreateReservationDto createReservationDto) {

    }

    @Override
    public List<ReservationDateHistory> getMyReservationDateHistory(String userId, String yearMonth) {
        return null;
    }

    @Override
    public void startReservationStatus(String reservationId) {

    }

    @Override
    protected boolean validateCancelPermission(String reservationId) {
        return false;
    }

    @Override
    protected boolean validateSearchPermission(String reservationId) {
        return false;
    }
}
