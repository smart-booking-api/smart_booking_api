package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.reservation.dto.ReservationSimpleResponse;
import com.smart.booking.domain.reservation.enums.SearchDateType;
import java.util.List;

public class AdminReservationServiceImpl extends CommonReservationService implements AdminReservationService{

    @Override
    public int getReservationCount(String storeId, String startDate, String endDate) {
        return 0;
    }

    @Override
    public List<ReservationSimpleResponse> getReservationList(String reservationUserName, String reservationNo) {
        return null;
    }

    @Override
    public void createPhoneReservation(String reservationUserName, String phone) {

    }

    @Override
    public void getMonthlyReservationDates(String storeId, SearchDateType dateType) {

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
