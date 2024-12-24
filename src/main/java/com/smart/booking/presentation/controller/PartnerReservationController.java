package com.smart.booking.presentation.controller;

import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.common.resolver.MemberContext;
import com.smart.booking.facade.common.reservation.GetEnableReservationTimeFacade;
import com.smart.booking.facade.dto.reservation.CreatePhoneReservationDto;
import com.smart.booking.facade.dto.reservation.GetPhoneReservationDto;
import com.smart.booking.facade.dto.reservation.ReservationTimeResponseDto;
import com.smart.booking.facade.partner.reservation.CancelReservationFacade;
import com.smart.booking.facade.partner.reservation.CreatePhoneReservationFacade;
import com.smart.booking.facade.partner.reservation.GetPhoneReservationFacade;
import com.smart.booking.presentation.controller.endPoint.PartnerReservationEndpoint;
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
@Tag(name = "파트너 예약", description = "파트너 예약 컨트롤러")
public class PartnerReservationController {
    private final CreatePhoneReservationFacade createPhoneReservationFacade;
    private final GetPhoneReservationFacade getPhoneReservationFacade;
    private final GetEnableReservationTimeFacade getEnableReservationTimeFacade;
    private final CancelReservationFacade cancelReservationFacade;

    @Operation(security = {@SecurityRequirement(name = "accessToken")}, summary = "전화예약생성", description = "전화예약인 경우 해당 API 로 예약을 생성한다.")
    @PostMapping(PartnerReservationEndpoint.PHONE_RESERVATION)
    public void createReservation(@MemberContext MemberContextDto memberContextDto, @RequestBody @Valid CreatePhoneReservationDto createDto) {
        createPhoneReservationFacade.execute(createDto, memberContextDto);
    }

    @Operation(security = {@SecurityRequirement(name = "accessToken")}, summary = "전화예약조회", description = "전화예약 내역을 조회한다..")
    @GetMapping(PartnerReservationEndpoint.PHONE_RESERVATION)
    public List<GetPhoneReservationDto> getReservation(@MemberContext MemberContextDto memberContextDto, @RequestParam String searchText) {
        return getPhoneReservationFacade.execute(searchText, memberContextDto.getMemberId());
    }

    @Operation(security = {@SecurityRequirement(name = "accessToken")}, summary = "시간조회", description = "예약이 가능하고 선점락이 걸려있지 않은 예약 가능 시간을 조회한다.")
    @GetMapping(PartnerReservationEndpoint.GET_ENABLE_RESERVATION_TIME)
    public List<ReservationTimeResponseDto> getEnableReservationTime(@RequestParam String teeBoxId, @RequestParam String reservationDate) {
        return getEnableReservationTimeFacade.execute(teeBoxId, reservationDate);
    }

    @Operation(security = {@SecurityRequirement(name = "accessToken")}, summary = "예약취소", description = "관리자 예약 취소")
    @DeleteMapping(PartnerReservationEndpoint.RESERVATION_PARTNER_CANCEL_RESERVATION)
    public void cancelReservation(@PathVariable String reservationId, @MemberContext MemberContextDto memberContextDto) {
        cancelReservationFacade.execute(reservationId, memberContextDto.getMemberId());
    }
}
