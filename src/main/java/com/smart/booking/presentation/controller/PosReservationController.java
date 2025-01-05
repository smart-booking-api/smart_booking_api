package com.smart.booking.presentation.controller;

import com.smart.booking.facade.dto.reservation.ReservationSimpleResponseDto;
import com.smart.booking.facade.user.reservation.GetReservationFacadeByReservationNo;
import com.smart.booking.presentation.controller.endPoint.PosReservationEndpoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "포스기 예약 관리", description = "포스기 예약 관리 컨트롤러")
public class PosReservationController {
    private final GetReservationFacadeByReservationNo getReservationFacadeByReservationNo;

    @Operation(summary = "예약번호로 예약 조회")
    @GetMapping(PosReservationEndpoint.GET_RESERVATION)
    public ReservationSimpleResponseDto getReservation(@PathVariable int reservationNo) {
        return getReservationFacadeByReservationNo.execute(reservationNo);
    }
}
