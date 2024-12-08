package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.reservation.dto.UpsertPhoneReservationDto;
import com.smart.booking.domain.reservation.repository.ReservationRepository;
import com.smart.booking.facade.dto.reservation.ReservationSimpleResponse;
import com.smart.booking.domain.reservation.enums.SearchDateType;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AdminReservationServiceImpl extends CommonReservationServiceImpl implements AdminReservationService {
    private final ReservationRepository reservationRepository;

    public AdminReservationServiceImpl(ReservationRepository reservationRepository, ReservationRepository reservationRepository1) {
        super(reservationRepository);
        this.reservationRepository = reservationRepository1;
    }

    @Override
    public int getReservationCount(String storeId, String startDate, String endDate) {
        return 0;
    }

    @Override
    public List<ReservationSimpleResponse> getReservationList(String reservationUserName, String reservationNo) {
        return null;
    }

    @Override
    public void createPhoneReservation(UpsertPhoneReservationDto upsertPhoneReservationDto) {

    }

    @Override
    public void getMonthlyReservationDates(String storeId, SearchDateType dateType) {

    }

    @Override
    public boolean validateCancelPermission(String reservationId) {
        return false;
    }

    @Override
    public boolean validateSearchPermission(String reservationId) {
        return false;
    }
}
