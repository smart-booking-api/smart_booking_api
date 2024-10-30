package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.reservation.dto.ReservationDateHistory;
import com.smart.booking.domain.reservation.dto.ReservationSimpleResponse;
import java.util.List;

public interface ReservationService {

    /**
     * 홈 내 예약현황 요약 조회
     * @param userId
     * @param startDate
     */
    List<ReservationSimpleResponse> getMyReservations(String userId, String startDate);

    /**
     * 예약 가능 타석 조회
     * @param storeId
     * @param reserveDate
     */
    void getEnableTeeBoxes(String storeId, String reserveDate);

    /**
     * 타석에 대한 예약 가능 시간 조회
     * @param storeId
     * @param reserveDate
     * @param teeBoxId
     */
    void getEnableTimes(String storeId, String reserveDate, String teeBoxId);

    /**
     * 선점조회
     * 시간을 클릭했을때 선점여부 조회
     * @param storeId
     * @param reserveDate
     * @param teeBoxId
     * @param timeCodeId
     * @return
     */
    boolean isOccupiedReservation(String storeId, String reserveDate, String teeBoxId, String timeCodeId);

    /**
     * 예약하기
     * @param storeId
     * @param reserveDate
     * @param teeBoxId
     * @param startTimeCodeId
     * @param endTimeCodeId
     */
    void createReservation(String storeId, String reserveDate, String teeBoxId, String startTimeCodeId, String endTimeCodeId);

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
}
