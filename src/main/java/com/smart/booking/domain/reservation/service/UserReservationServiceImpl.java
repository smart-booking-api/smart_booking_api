package com.smart.booking.domain.reservation.service;

import com.smart.booking.common.util.CommonUtil;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.reservation.dto.UpsertReservationDto;
import com.smart.booking.domain.reservation.dto.ReservationDateHistoryDto;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.repository.ReservationRepository;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class UserReservationServiceImpl extends CommonReservationServiceImpl implements UserReservationService {
    private final ReservationRepository reservationRepository;

    public UserReservationServiceImpl(ReservationRepository reservationRepository) {
        super(reservationRepository);
        this.reservationRepository = reservationRepository;
    }

    /**
     * startDate 부터 이후 예약 조회
     * @param member
     * @param startDate
     * @return
     */
    @Override
    public List<Reservation> getMyReservations(Member member, String startDate) {
        return reservationRepository.findAllByReservationMemberMemberAndReservationDateIsAfter(member, LocalDate.parse(startDate));
    }

    /**
     * 예약생성
     * @param upsertReservationDto
     */
    @Override
    public void createReservation(UpsertReservationDto upsertReservationDto) {
        var randomNumber = CommonUtil.createRandomNumber();

        while(Objects.isNull(reservationRepository.findByReservationNo(randomNumber))) {
            randomNumber = CommonUtil.createRandomNumber();
        }

        Reservation reservation = upsertReservationDto.toEntity(randomNumber);
        reservationRepository.save(reservation);
    }

    @Override
    public List<ReservationDateHistoryDto> getMyReservationDateHistory(String memberId, String yearMonth) {
        return null;
    }

    @Override
    public void startReservationStatus(String reservationId) {

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
