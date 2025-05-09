package com.smart.booking.facade.common.reservation;

import com.smart.booking.domain.reservation.dto.UpsertReservationLockDto;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.entity.ReservationTimeCode;
import com.smart.booking.domain.reservation.service.CommonReservationService;
import com.smart.booking.domain.reservation.service.CommonReservationServiceImpl;
import com.smart.booking.domain.reservation.service.ReservationLockService;
import com.smart.booking.domain.reservation.service.ReservationTimeService;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.domain.tee_box.service.TeeBoxCommonService;
import com.smart.booking.facade.dto.reservation.ReservationTimeResponseDto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GetEnableReservationTimeFacade {
    private final TeeBoxCommonService teeBoxCommonService;
    private final ReservationTimeService reservationTimeService;
    private final CommonReservationService commonReservationService;
    private final ReservationLockService reservationLockService;

    public GetEnableReservationTimeFacade(TeeBoxCommonService teeBoxCommonService, ReservationTimeService reservationTimeService,
        @Qualifier("commonReservationServiceImpl") CommonReservationService commonReservationServiceImpl, ReservationLockService reservationLockService) {
        this.teeBoxCommonService = teeBoxCommonService;
        this.reservationTimeService = reservationTimeService;
        this.commonReservationService = commonReservationServiceImpl;
        this.reservationLockService = reservationLockService;
    }

    public List<ReservationTimeResponseDto> execute(String teeBoxId, String reservationDate) {
        // 타석 운영시간 구하기
        List<ReservationTimeCode> timeCodes = getTeeBoxOperationTimes(teeBoxId);

        // 타석 해당날짜에 예약되어있는 내역 구하기
        Set<String> reservedTimeIds = getReservedTimeIds(teeBoxId, reservationDate);

        // 타석에 예약되어 있는 시간 빼기
        List<ReservationTimeCode> reservableTimeCodes = reservableTimeCodes(timeCodes, reservedTimeIds);

        // 선점락 걸려있지 않은 시간 조회
        return getUnlockedReservationTimeCodes(reservableTimeCodes, teeBoxId, reservationDate)
            .stream().map(item -> new ReservationTimeResponseDto(item.getId(), item.getTimeName()))
            .collect(Collectors.toList());
    }

    private List<ReservationTimeCode> getTeeBoxOperationTimes(String teeBoxId) {
        TeeBox teeBox = teeBoxCommonService.getTeeBoxById(teeBoxId);
        // todo 타석 서비스 구현 뒤 제거 예정
        String openTime = "06:00";
        String closeTime = "11:00";
//        String openTime = teeBox.getStore().getOperationInfo().getOpenTime();
//        String closeTime = teeBox.getStore().getOperationInfo().getCloseTime();

        ReservationTimeCode openTimeCode = reservationTimeService.getReservationTimeByTimeName(openTime);
        ReservationTimeCode closeTimeCode = reservationTimeService.getReservationTimeByTimeName(closeTime);

        return getTimesBetweenStartAndEnd(openTimeCode.getId(), closeTimeCode.getId());
    }

    private List<ReservationTimeCode> getTimesBetweenStartAndEnd(String openTimeId, String closeTimeId) {
        return reservationTimeService.getReservationTimeBetweenStartAndEnd(openTimeId, closeTimeId);
    }

    private Set<String> getReservedTimeIds(String teeBoxId, String searchDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate reservationDate = LocalDate.parse(searchDate, formatter);

        TeeBox teeBox = teeBoxCommonService.getTeeBoxById(teeBoxId);

        List<Reservation> reservations = commonReservationService.getReservationByTeeBoxAndReservationDate(teeBox, reservationDate);

        return reservations.stream()
            .flatMap(reservation -> getReservationTimeIds(reservation).stream())
            .collect(Collectors.toSet());
    }

    private Set<String> getReservationTimeIds(Reservation reservation) {
        List<ReservationTimeCode> timeCodes = reservationTimeService.getReservationTimeBetweenStartAndEnd(reservation.getStartTimeId(), reservation.getEndTimeId());
        return timeCodes.stream()
            .map(ReservationTimeCode::getId)
            .collect(Collectors.toSet());
    }

    private List<ReservationTimeCode> reservableTimeCodes(List<ReservationTimeCode> timeCodes, Set<String> reservedTimeIds) {
        return timeCodes.stream()
            .filter(timeCode -> !reservedTimeIds.contains(timeCode.getId()))
            .collect(Collectors.toList());
    }

    private List<ReservationTimeCode> getUnlockedReservationTimeCodes(List<ReservationTimeCode> reservableTimeCodes, String teeBoxId, String reservationDate) {
        return reservableTimeCodes.stream()
            .filter(reservationTimeCode ->
                Objects.isNull(reservationLockService.getReservationLock(
                    UpsertReservationLockDto.builder()
                        .date(reservationDate)
                        .lockTimeId(reservationTimeCode.getId())
                        .teeBoxId(teeBoxId)
                        .build())))
            .collect(Collectors.toList());
    }
}
