package com.smart.booking.facade.admin.reservation;

import com.smart.booking.common.dto.MemberContext;
import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.reservation.dto.UpsertPhoneReservationDto;
import com.smart.booking.domain.reservation.dto.UpsertReservationLockDto;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.entity.ReservationTimeCode;
import com.smart.booking.domain.reservation.service.AdminReservationService;
import com.smart.booking.domain.reservation.service.ReservationLockService;
import com.smart.booking.domain.reservation.service.ReservationTimeService;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.domain.tee_box.service.TeeBoxCommonService;
import com.smart.booking.facade.dto.reservation.CreatePhoneReservationDto;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreatePhoneReservationFacade {
    private final AdminReservationService adminReservationService;
    private final ReservationLockService reservationLockService;
    private final ReservationTimeService reservationTimeService;
    private final TeeBoxCommonService teeBoxCommonService;

    @Transactional
    public void execute(@NonNull CreatePhoneReservationDto createDto, MemberContext memberContext) {
        // 선점락 생성
        createReservationLock(createDto, memberContext.getMemberId());

        // 예약조회
        validateReservation(createDto);

        // 예약처리
        UpsertPhoneReservationDto dto = convertToUpsertPhoneReservationDto(createDto, memberContext.getMemberId());
        adminReservationService.createPhoneReservation(dto);

        // 락 해제
        deleteReservationLock(createDto, memberContext.getMemberId());
    }

    private void createReservationLock(CreatePhoneReservationDto createDto, String memberId) {
        List<ReservationTimeCode> reservationTimeCodes = getReservationTimeBetweenStartAndEnd(createDto.startTimeTableId(), createDto.endTimeTableId());

        for (ReservationTimeCode reservationTime : reservationTimeCodes) {
            UpsertReservationLockDto upsertReservationLockDto = convertUpsertReservationLockDto(createDto.teeBoxId(), createDto.reservationDate(), reservationTime.getId(), memberId);
            reservationLockService.createReservationLock(upsertReservationLockDto);
        }
    }

    private List<ReservationTimeCode> getReservationTimeBetweenStartAndEnd(String startTimeId, String endTimeId) {
        return reservationTimeService.getReservationTimeBetweenStartAndEnd(startTimeId, endTimeId);
    }

    private void validateReservation(CreatePhoneReservationDto createDto) {
        List<Reservation> reservations = getReservation(createDto.teeBoxId(), createDto.reservationDate());

        for (Reservation reservation : reservations) {
            LocalTime reservedStartTime = getTime(reservation.getStartTimeId()).toLocalTime();
            LocalTime reservedEndTime = getTime(reservation.getEndTimeId()).toLocalTime();

            LocalTime checkStartTime = getTime(createDto.startTimeTableId()).toLocalTime();
            LocalTime checkEndTime = getTime(createDto.endTimeTableId()).toLocalTime();

            checkTimeRangeWithin(reservedStartTime, reservedEndTime, checkStartTime, checkEndTime);
        }
    }

    private void checkTimeRangeWithin(LocalTime reservedStartTime, LocalTime reservedEndTime, LocalTime checkStartTime, LocalTime checkEndTime) {
        if (!checkStartTime.isBefore(reservedStartTime) && !checkEndTime.isAfter(reservedEndTime)) {
            throw new CommonException(ResponseCode.ALREADY_LOCK_RESERVATION);
        }
    }

    private List<Reservation> getReservation(String teeBoxId, String searchDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate reservationDate = LocalDate.parse(searchDate, formatter);

        TeeBox teeBox = teeBoxCommonService.getTeeBoxById(teeBoxId);

        return adminReservationService.getReservationByTeeBoxAndReservationDate(teeBox, reservationDate);
    }

    private Time getTime(String timeId) {
        return reservationTimeService.getReservationTimeCodeById(timeId).getTime();
    }

    private UpsertPhoneReservationDto convertToUpsertPhoneReservationDto(CreatePhoneReservationDto createDto, String memberId) {
        return new UpsertPhoneReservationDto(createDto, memberId);
    }

    private void deleteReservationLock(CreatePhoneReservationDto createDto, String memberId) {
        List<ReservationTimeCode> reservationTimeCodes = getReservationTimeBetweenStartAndEnd(createDto.startTimeTableId(), createDto.endTimeTableId());

        for (ReservationTimeCode reservationTime : reservationTimeCodes) {
            UpsertReservationLockDto upsertReservationLockDto = convertUpsertReservationLockDto(createDto.teeBoxId(), createDto.reservationDate(), reservationTime.getId(), memberId);
            reservationLockService.deleteReservationLock(upsertReservationLockDto);
        }
    }

    private UpsertReservationLockDto convertUpsertReservationLockDto(String teeBoxId, String date, String timeId, String memberId) {
        return UpsertReservationLockDto.builder()
            .teeBoxId(teeBoxId)
            .date(date)
            .lockTimeId(timeId)
            .memberId(memberId)
            .build();
    }
}
