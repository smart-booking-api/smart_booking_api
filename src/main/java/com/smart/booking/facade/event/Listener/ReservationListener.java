package com.smart.booking.facade.event.Listener;

import com.smart.booking.facade.event.dto.CompletePaymentEventDto;
import com.smart.booking.facade.user.reservation.CreateReservationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ReservationListener {

    private final CreateReservationFacade createReservationFacade;

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createReservation(CompletePaymentEventDto completePaymentEventDto) {
        createReservationFacade.createReservation(completePaymentEventDto);
    }
}
