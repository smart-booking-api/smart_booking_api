package com.smart.booking.facade.admin.reservation;

import com.smart.booking.common.dto.MemberContext;
import com.smart.booking.domain.reservation.service.AdminReservationService;
import com.smart.booking.facade.dto.reservation.CreatePhoneReservationDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatePhoneReservationFacade {
    private final AdminReservationService adminReservationService;

    public void execute(@NonNull CreatePhoneReservationDto createDto, MemberContext memberContext) {
//        adminReservationService.createPhoneReservation();
    }
}
