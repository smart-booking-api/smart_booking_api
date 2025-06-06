package com.smart.booking.domain.reservation.service;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.reservation.dto.ReservationDetailResponseDto;
import com.smart.booking.domain.reservation.dto.TimeCodeDto;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.enums.ReservationStatus;
import com.smart.booking.domain.reservation.repository.ReservationRepository;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonReservationServiceImpl implements CommonReservationService {
    private final ReservationRepository reservationRepository;

    /**
     * 예약 조회
     */
    @Override
    public List<Reservation> getReservationByTeeBoxAndDateAndTimeIds(TeeBox teeBox, LocalDate reservationDate, TimeCodeDto timeCodeDto) {
//        // 타석 해당날짜에 예약되어있는 내역 구하기
//        Set<String> reservedTimeIds = getReservedTimeIds(teeBoxId, reservationDate);
//
//        // 타석에 예약되어 있는 시간 빼기
//        List<ReservationTimeCode> reservableTimeCodes = reservableTimeCodes(timeCodes, reservedTimeIds);
//
//        return reservationRepository.findAllByStoreAndReservationDate(store, reservationDate);
        return null;
    }

    /**
     * 타석, 날짜로 예약 검색(가능시간 조회)
     * @param teeBox
     * @param reservationDate
     * @return
     */
    @Override
    public List<Reservation> getReservationByTeeBoxAndReservationDate(TeeBox teeBox, LocalDate reservationDate) {
        return reservationRepository.findReservationByTeeBoxAndReservationDate(teeBox, reservationDate);
    }

    /**
     * 예약번호로 검색
     * @param reservationNo
     * @return
     */
    @Override
    public Optional<Reservation> findByReservationNo(int reservationNo) {
        return reservationRepository.findByReservationNo(reservationNo);
    }

    /**
     * 예약 취소
     * @param reservationId
     */
    @Override
    public void cancelReservation(String reservationId, String memberId, MemberType memberType) {
        if (validateCancelPermission(reservationId, memberId, memberType)) {
            Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_RESERVATION));

            reservation.updateReservationStatus(ReservationStatus.CANCELED);
        }
    }

    /**
     * 예약 상세조회
     * @param reservationId
     * @return
     */
    public ReservationDetailResponseDto getReservationDetail(String reservationId, String memberId) {
        // todo 예약 조회
        if (validateSearchPermission(reservationId, memberId)) {
            // admin : 관리자체크 , user: 자기 예약인지 체크
        }
        return null;
    }

    @Override
    public boolean validateCancelPermission(String reservationId, String memberId, MemberType memberType) {
        return true;
    };

    @Override
    public boolean validateSearchPermission(String reservationId, String memberId) {
        return true;
    }

    @Override
    public Reservation getReservationByTrackingId(String trackingId) {
        return reservationRepository.findReservationByTrackingId(trackingId)
            .orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_RESERVATION));
    }
}
