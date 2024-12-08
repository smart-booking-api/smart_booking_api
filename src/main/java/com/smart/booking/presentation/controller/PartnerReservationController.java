package com.smart.booking.presentation.controller;

import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.common.resolver.MemberContext;
import com.smart.booking.facade.admin.reservation.CreatePhoneReservationFacade;
import com.smart.booking.facade.common.reservation.GetEnableReservationTimeFacade;
import com.smart.booking.facade.dto.reservation.CreatePhoneReservationDto;
import com.smart.booking.facade.dto.reservation.ReservationTimeResponse;
import com.smart.booking.presentation.controller.endPoint.PartnerReservationEndpoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "파트너 예약", description = "파트너 예약 컨트롤러")
public class PartnerReservationController {
    private final CreatePhoneReservationFacade createPhoneReservationFacade;
    private final GetEnableReservationTimeFacade getEnableReservationTimeFacade;

    @Operation(security = {@SecurityRequirement(name = "accessToken")}, summary = "전화예약생성", description = "전화예약인 경우 해당 API 로 예약을 생성한다.")
    @PostMapping(PartnerReservationEndpoint.RESERVATION_PARTNER_CREATE_PHONE_RESERVATION)
    public void createReservation(@RequestBody @Valid CreatePhoneReservationDto createDto, @MemberContext MemberContextDto memberContextDto) {
        createPhoneReservationFacade.execute(createDto, memberContextDto);
    }

    @Operation(security = {@SecurityRequirement(name = "accessToken")}, summary = "시간조회", description = "예약이 가능하고 선점락이 걸려있지 않은 예약 가능 시간을 조회한다.")
    @GetMapping(PartnerReservationEndpoint.GET_ENABLE_RESERVATION_TIME)
    public List<ReservationTimeResponse> getEnableReservationTime(@RequestParam String teeBoxId, @RequestParam String reservationDate) {
        return getEnableReservationTimeFacade.execute(teeBoxId, reservationDate);
    }
}
