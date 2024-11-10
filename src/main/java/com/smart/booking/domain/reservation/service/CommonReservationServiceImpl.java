package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.reservation.dto.ReservationDetailResponse;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.repository.ReservationRepository;
import com.smart.booking.domain.store.entity.Store;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class CommonReservationServiceImpl implements CommonReservationService {
    private final ReservationRepository reservationRepository;

    /**
     * 예약 조회
     * @param store
     * @param reservationDate
     */
    @Override
    public List<Reservation> getReservationByStoreAndReservationDate(Store store, LocalDate reservationDate) {
        return reservationRepository.findAllByStoreAndReservationDate(store, reservationDate);
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
    public ReservationDetailResponse getReservationDetail(String reservationId) {
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

    abstract boolean validateCancelPermission(String reservationId);

    abstract boolean validateSearchPermission(String reservationId);
}
