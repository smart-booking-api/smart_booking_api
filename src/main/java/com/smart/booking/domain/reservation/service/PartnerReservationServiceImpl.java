package com.smart.booking.domain.reservation.service;

import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.reservation.dto.UpsertPhoneReservationDto;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.repository.ReservationRepository;
import com.smart.booking.facade.dto.reservation.ReservationSimpleResponse;
import com.smart.booking.domain.reservation.enums.SearchDateType;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PartnerReservationServiceImpl extends CommonReservationServiceImpl implements PartnerReservationService {
    private final ReservationRepository reservationRepository;

    public PartnerReservationServiceImpl(ReservationRepository reservationRepository, ReservationRepository reservationRepository1) {
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
        Reservation reservation = upsertPhoneReservationDto.toEntity();
        reservationRepository.save(reservation);
    }

    @Override
    public void getMonthlyReservationDates(String storeId, SearchDateType dateType) {

    }

    @Override
    public boolean validateCancelPermission(String reservationId, String memberId, MemberType memberType) {
        return memberType == MemberType.PARTNER;
    }

    @Override
    public boolean validateSearchPermission(String reservationId, String memberId) {
        return false;
    }
}
