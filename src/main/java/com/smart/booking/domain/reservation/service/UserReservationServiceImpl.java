package com.smart.booking.domain.reservation.service;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.common.util.CommonUtil;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.reservation.dto.UpsertReservationDto;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.enums.ReservationStatus;
import com.smart.booking.domain.reservation.repository.ReservationRepository;
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
    public List<Reservation> getMonthlyMyReservationHistory(String memberId, String year, String month) {
        return reservationRepository.findByMemberIdAndYearMonth(memberId, year.concat(month));
    }

    @Override
    public void startReservationStatus(String reservationId) {

    }

    @Override
    public Reservation getReservationById(String reservationId) {
        return reservationRepository.findById(reservationId).orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_RESERVATION));
    }

    @Override
    public Reservation getReservationByReservationNo(int reservationNo) {
        return reservationRepository.findByReservationNo(reservationNo).orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_RESERVATION));
    }

    @Override
    public void updateReservationStatus(int reservationNo, ReservationStatus reservationStatus) {
        Reservation reservation = reservationRepository.findByReservationNo(reservationNo)
            .orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_RESERVATION));
        reservation.updateReservationStatus(reservationStatus);
    }

    @Override
    public boolean validateCancelPermission(String reservationId, String memberId, MemberType memberType) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_RESERVATION));
        return reservation.getCreatedBy().equals(memberId);
    }

    @Override
    public boolean validateSearchPermission(String reservationId, String memberId) {
        return false;
    }
}
