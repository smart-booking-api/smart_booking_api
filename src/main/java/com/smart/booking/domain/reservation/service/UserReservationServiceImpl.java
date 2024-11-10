package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.reservation.dto.CreateReservationDto;
import com.smart.booking.domain.reservation.dto.ReservationDateHistory;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.repository.ReservationRepository;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.facade.dto.reservation.ReservationSimpleResponse;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class UserReservationServiceImpl extends CommonReservationServiceImpl implements UserReservationService {
    private ReservationRepository reservationRepository;

    public UserReservationServiceImpl(ReservationRepository reservationRepository) {
        super(reservationRepository);
    }

    @Override
    public List<Reservation> getMyReservations(Member member, String startDate) {
        return reservationRepository.findAllByReservationMemberMemberAndReservationDateIsAfter(member, LocalDate.parse(startDate));
    }

    @Override
    public void createReservation(CreateReservationDto createReservationDto) {
        // todo

    }

    @Override
    public List<ReservationDateHistory> getMyReservationDateHistory(String memberId, String yearMonth) {
        return null;
    }

    @Override
    public void startReservationStatus(String reservationId) {

    }

    @Override
    public List<Reservation> getReservationByStoreAndReservationDate(Store store, LocalDate reservationDate) {
        return getReservationByStoreAndReservationDate(store, reservationDate);
    }

    @Override
    boolean validateCancelPermission(String reservationId) {
        return false;
    }

    @Override
    boolean validateSearchPermission(String reservationId) {
        return false;
    }
}
