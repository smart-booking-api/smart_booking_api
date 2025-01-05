package com.smart.booking.facade.event.listener;

import com.smart.booking.facade.event.dto.RefundPaymentEventDto;
import com.smart.booking.facade.event.dto.CompletePaymentEventDto;
import com.smart.booking.facade.user.reservation.RefundReservationFacade;
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
    private final RefundReservationFacade refundReservationFacade;

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createReservation(CompletePaymentEventDto completePaymentEventDto) throws Exception {
        createReservationFacade.execute(completePaymentEventDto);
    }

    // todo 제거
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void cancelReservation(RefundPaymentEventDto refundPaymentEventDto) throws Exception {
        refundReservationFacade.execute(refundPaymentEventDto);
    }

    // todo 결제실패 - 락 제거
}
