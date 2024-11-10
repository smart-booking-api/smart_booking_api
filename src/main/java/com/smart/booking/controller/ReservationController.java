package com.smart.booking.controller;

import com.smart.booking.common.dto.MemberContext;
import com.smart.booking.controller.endPoint.ReservationEndpoint;
import com.smart.booking.domain.reservation.dto.ReservationLockDto;
import com.smart.booking.facade.user.reservation.GetEnableReservationTeeBoxFacade;
import com.smart.booking.facade.dto.reservation.ReservationSimpleResponse;
import com.smart.booking.facade.common.reservation.CreateReservationLockFacade;
import com.smart.booking.facade.common.reservation.DeleteReservationLockFacade;
import com.smart.booking.facade.common.reservation.CheckReservationLockFacade;
import com.smart.booking.facade.user.reservation.GetReservationFacade;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final CheckReservationLockFacade checkReservationLockFacade;
    private final CreateReservationLockFacade createReservationLockFacade;
    private final DeleteReservationLockFacade deleteReservationLockFacade;
    private final GetReservationFacade getReservationFacade;
    private final GetEnableReservationTeeBoxFacade getEnableReservationTeeBoxFacade;

    @GetMapping(ReservationEndpoint.RESERVATION_CHECK_LOCK)
    public boolean checkReservationLock(@RequestParam String storeId, @RequestParam String timeId, MemberContext memberContext) {
        var dto = new ReservationLockDto.Get(storeId, timeId);
        return checkReservationLockFacade.execute(dto, memberContext);
    }

    @PostMapping(ReservationEndpoint.RESERVATION_CREATE_LOCK)
    public void createReservationLock(@NonNull @RequestBody ReservationLockDto.Create createDto, MemberContext memberContext) {
        createReservationLockFacade.execute(createDto, memberContext);
    }

    @DeleteMapping(ReservationEndpoint.RESERVATION_DELETE_LOCK)
    public void deleteReservationLock(@NonNull @RequestBody ReservationLockDto.Delete deleteDto) {
        deleteReservationLockFacade.execute(deleteDto);
    }

    @GetMapping(ReservationEndpoint.GET_ENABLE_RESERVATION_TEE_BOX)
    public List<String> getEnableReservationTeeBox(String startDate) {
        return null;
    }

    @GetMapping(ReservationEndpoint.GET_MY_RESERVATION)
    public List<ReservationSimpleResponse> getMyReservations(MemberContext memberContext, String startDate) {
        return getReservationFacade.getMyReservations(memberContext.getMemberId(), startDate);
    }
}
