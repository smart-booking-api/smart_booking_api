package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.reservation.dto.ReservationDetailResponseDto;
import com.smart.booking.domain.reservation.dto.TimeCodeDto;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.entity.ReservationTimeCode;
import com.smart.booking.domain.reservation.repository.ReservationRepository;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
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
        return reservationRepository.getReservationByTeeBoxAndReservationDate(teeBox, reservationDate);
    }

    /**
     * 예약 취소
     * @param reservationId
     */
    public void cancelReservation(String reservationId) {
        if (validateCancelPermission(reservationId)) {
            // todo 예약취소 로직
            // admin : 관리자체크 , user: 자기 예약인지 체크
        }
    }

    /**
     * 예약 상세조회
     * @param reservationId
     * @return
     */
    public ReservationDetailResponseDto getReservationDetail(String reservationId) {
        // todo 예약 조회
        if (validateSearchPermission(reservationId)) {
            // admin : 관리자체크 , user: 자기 예약인지 체크
        }
        return null;
    }

    /**
     * 타석에 대한 예약 가능 시간 조회
     * @param storeId
     * @param reservationDate
     * @param teeBoxId
     */
    public List<String> getEnableTimes(String storeId, String reservationDate, String teeBoxId) {
        return null;
    }

    @Override
    public boolean validateCancelPermission(String reservationId) {
        return true;
    };

    @Override
    public boolean validateSearchPermission(String reservationId) {
        return true;
    };
}
