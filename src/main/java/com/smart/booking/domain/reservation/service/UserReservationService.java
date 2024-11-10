package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.reservation.dto.CreateReservationDto;
import com.smart.booking.domain.reservation.dto.ReservationDateHistory;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.store.entity.Store;
import java.time.LocalDate;
import java.util.List;

public interface UserReservationService {

    /**
     * 홈 내 예약현황 요약 조회
     * @param member
     * @param startDate
     */
    List<Reservation> getMyReservations(Member member, String startDate);

    /**
     * 예약하기
     * @param createReservationDto
     */
    void createReservation(CreateReservationDto createReservationDto);

    /**
     * 월별 내 예약이력
     * @param userId
     * @param yearMonth
     * @return
     */
    List<ReservationDateHistory> getMyReservationDateHistory(String userId, String yearMonth);

    /**
     * 예약상태 변경(게임시작)
     * @param reservationId
     */
    void startReservationStatus(String reservationId);

    List<Reservation> getReservationByStoreAndReservationDate(Store store, LocalDate reservationDate);
}
