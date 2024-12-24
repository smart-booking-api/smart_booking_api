package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.reservation.dto.PhoneReservationDto;
import com.smart.booking.domain.reservation.dto.UpsertPhoneReservationDto;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.repository.ReservationRepository;
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
    public List<PhoneReservationDto> getPhoneReservationList(String searchText, String memberId) {
        return reservationRepository.findBySearchTextAndMemberId(searchText, memberId);
    }

    @Override
    public void createPhoneReservation(UpsertPhoneReservationDto upsertPhoneReservationDto) {
        Reservation reservation = upsertPhoneReservationDto.toEntity();
        reservationRepository.save(reservation);
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
