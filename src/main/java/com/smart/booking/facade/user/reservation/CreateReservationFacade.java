package com.smart.booking.facade.user.reservation;

import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.facade.dto.payment.CompletePaymentRequestDto;
import com.smart.booking.facade.event.dto.CompletePaymentEventDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateReservationFacade {

    private final UserReservationService userReservationService;

    public void createReservation(@NonNull CompletePaymentEventDto completePaymentEventDto) {

        // 예약
//        userReservationService.createReservation();
        // firebase 예약완료 전송
    }
}
