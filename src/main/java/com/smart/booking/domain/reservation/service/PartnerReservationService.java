package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.reservation.dto.UpsertPhoneReservationDto;
import com.smart.booking.facade.dto.reservation.ReservationSimpleResponse;
import com.smart.booking.domain.reservation.enums.SearchDateType;
import java.util.List;

public interface PartnerReservationService extends CommonReservationService {

    /**
     * 주간 예약 건수
     * @param storeId
     * @param startDate
     * @param endDate
     * @return
     */
    int getReservationCount(String storeId, String startDate, String endDate);

    /**
     * 전화예약 목록 조회
     * @param reservationUserName
     * @param reservationNo
     * @return
     */
    List<ReservationSimpleResponse> getReservationList(String reservationUserName, String reservationNo);

    /**
     * 전화예약 생성
     * @param upsertPhoneReservationDto
     */
    void createPhoneReservation(UpsertPhoneReservationDto upsertPhoneReservationDto);

    /**
     * 월별 예약 관리
     * @param storeId
     * @param dateType
     */
    void getMonthlyReservationDates(String storeId, SearchDateType dateType);
}
