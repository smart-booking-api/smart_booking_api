package com.smart.booking.presentation.controller;

import com.smart.booking.common.dto.MemberContext;
import com.smart.booking.facade.dto.reservation.CreatePhoneReservationDto;
import com.smart.booking.facade.dto.reservation.CreateReservationLockDto;
import com.smart.booking.facade.admin.reservation.CreatePhoneReservationFacade;
import com.smart.booking.facade.common.reservation.CreateReservationLockFacade;
import com.smart.booking.facade.common.reservation.DeleteReservationLockFacade;
import com.smart.booking.facade.dto.reservation.ReservationSimpleResponse;
import com.smart.booking.facade.dto.reservation.ReservationTimeResponse;
import com.smart.booking.facade.common.reservation.GetEnableReservationTimeFacade;
import com.smart.booking.facade.user.reservation.GetReservationFacade;
import com.smart.booking.presentation.controller.endPoint.ReservationEndpoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "예약", description = "예약 컨트롤러")
public class ReservationController {
    private final CreatePhoneReservationFacade createPhoneReservationFacade;
    private final CreateReservationLockFacade createReservationLockFacade;
    private final DeleteReservationLockFacade deleteReservationLockFacade;
    private final GetReservationFacade getReservationFacade;
    private final GetEnableReservationTimeFacade getEnableReservationTimeFacade;

    @Operation(security = {@SecurityRequirement(name = "accessToken")}, summary = "선점락 생성", description = "이용자 예약시 선점락 생성")
    @PostMapping(ReservationEndpoint.RESERVATION_LOCK)
    public void createReservationLock(@RequestBody @Valid CreateReservationLockDto lockDto, MemberContext memberContext) {
        createReservationLockFacade.execute(lockDto, memberContext.getMemberId());
    }

    @Operation(security = {@SecurityRequirement(name = "accessToken")}, summary = "예약생성", description = "전화예약인 경우 해당 API 로 예약을 생성한다.")
    @PostMapping(ReservationEndpoint.RESERVATION_CREATE_PHONE_RESERVATION)
    public void createReservation(@RequestBody @Valid CreatePhoneReservationDto createDto, MemberContext memberContext) {
        createPhoneReservationFacade.execute(createDto, memberContext);
    }

    @Operation(security = {@SecurityRequirement(name = "accessToken")}, summary = "선점락 제거", description = "예약을 취소한다.")
    @DeleteMapping(ReservationEndpoint.RESERVATION_LOCK)
    public void deleteReservationLock(@RequestBody @Valid CreateReservationLockDto deleteDto, MemberContext memberContext) {
        deleteReservationLockFacade.execute(deleteDto, memberContext.getMemberId());
    }

    @Operation(security = {@SecurityRequirement(name = "accessToken")}, summary = "예약조회", description = "메인화면 - 내 예약을 조회한다.")
    @GetMapping(ReservationEndpoint.GET_MY_RESERVATION)
    public List<ReservationSimpleResponse> getMyReservations(MemberContext memberContext, @PathVariable String startDate) {
        return getReservationFacade.getMyReservations(memberContext.getMemberId(), startDate);
    }

    @Operation(security = {@SecurityRequirement(name = "accessToken")}, summary = "시간조회", description = "예약이 가능하고 선점락이 걸려있지 않은 예약 가능 시간을 조회한다.")
    @GetMapping(ReservationEndpoint.GET_ENABLE_RESERVATION_TIME)
    public List<ReservationTimeResponse> getEnableReservationTime(@RequestParam String teeBoxId, @RequestParam String reservationDate) {
        return getEnableReservationTimeFacade.execute(teeBoxId, reservationDate);
    }
}
