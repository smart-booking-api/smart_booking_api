package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.reservation.dto.UpsertReservationDto;
import com.smart.booking.domain.reservation.entity.Reservation;
import java.util.List;

public interface UserReservationService extends CommonReservationService {

    /**
     * 홈 내 예약현황 요약 조회(오늘이후)
     * @param member
     * @param startDate
     */
    List<Reservation> getMyReservations(Member member, String startDate);

    /**
     * 예약하기
     * @param upsertReservationDto
     */
    void createReservation(UpsertReservationDto upsertReservationDto);

    /**
     * 월별 내 예약이력
     * @param memberId
     * @param year
     * @param month
     * @return
     */
    List<Reservation> getMonthlyMyReservationHistory(String memberId, String year, String month);

    /**
     * 예약상태 변경(게임시작)
     * @param reservationId
     */
    void startReservationStatus(String reservationId);

    /**
     * 예약 ID로 예약조회
     * @param reservationId
     * @return
     */
    Reservation getReservationById(String reservationId);
}
