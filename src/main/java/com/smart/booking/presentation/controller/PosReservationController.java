package com.smart.booking.presentation.controller;

import com.smart.booking.facade.common.reservation.CreateReservationLockFacade;
import com.smart.booking.facade.common.reservation.GetEnableReservationTimeFacade;
import com.smart.booking.facade.dto.reservation.CreateReservationLockDto;
import com.smart.booking.facade.dto.reservation.GetReservationTime;
import com.smart.booking.facade.dto.reservation.ReservationSimpleResponseDto;
import com.smart.booking.facade.dto.reservation.ReservationTimeResponseDto;
import com.smart.booking.facade.pos.reservation.UpdateReservationGameFacade;
import com.smart.booking.facade.user.reservation.GetReservationByReservationNoFacade;
import com.smart.booking.presentation.controller.endPoint.PosReservationEndpoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "포스기 예약 관리", description = "포스기 예약 관리 컨트롤러")
public class PosReservationController {
    private final GetReservationByReservationNoFacade getReservationByReservationNoFacade;
    private final UpdateReservationGameFacade updateReservationGameFacade;
    private final GetEnableReservationTimeFacade getEnableReservationTimeFacade;
    private final CreateReservationLockFacade createReservationLockFacade;

    @Operation(summary = "예약이 가능하고 선점락이 걸려있지 않은 예약 가능 시간을 조회한다.")
    @GetMapping(PosReservationEndpoint.GET_ENABLE_RESERVATION_TIME)
    public List<ReservationTimeResponseDto> getEnableReservationTime(GetReservationTime getReservationTime) {
        return getEnableReservationTimeFacade.execute(getReservationTime.teeBoxId(), getReservationTime.reservationDate());
    }

    @Operation(summary = "선점락 + firebase data 생성", description = "바로 입장시 선점락 생성")
    @PostMapping(PosReservationEndpoint.RESERVATION_LOCK)
    public void createReservationLock(@RequestBody @Valid CreateReservationLockDto lockDto) {
        createReservationLockFacade.execute(lockDto, lockDto.posId());
    }

    @Operation(summary = "예약입장 - 예약번호로 예약 조회")
    @GetMapping(PosReservationEndpoint.GET_RESERVATION)
    public ReservationSimpleResponseDto getReservation(@PathVariable int reservationNo) {
        return getReservationByReservationNoFacade.execute(reservationNo);
    }

    @Operation(summary = "예약번호로 게임 시작")
    @PutMapping(PosReservationEndpoint.START_GAME)
    public void startGameByReservationNo(@PathVariable int reservationNo) {
        updateReservationGameFacade.execute(reservationNo);
    }
}
