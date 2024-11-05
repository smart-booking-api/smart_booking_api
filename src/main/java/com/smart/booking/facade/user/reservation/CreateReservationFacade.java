package com.smart.booking.facade.user.reservation;

import com.smart.booking.domain.reservation.dto.CreateReservationDto;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.domain.user.service.UserService;
import com.smart.booking.facade.dto.payment.CompletePaymentRequestDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateReservationFacade {
    private final UserReservationService userReservationService;

    public void createReservation(@NonNull CompletePaymentRequestDto createReservationDto) {

        // 예약
//        userReservationService.createReservation();
        // firebase 예약완료 전송
    }
}
