package com.smart.booking.facade.event.listener;

import com.smart.booking.facade.event.dto.CompletePaymentEventDto;
import com.smart.booking.facade.event.dto.FailedPaymentEventDto;
import com.smart.booking.facade.event.dto.RefundPaymentEventDto;
import com.smart.booking.facade.partner.reservation.FailReservationFacade;
import com.smart.booking.facade.user.reservation.CreateReservationFacade;
import com.smart.booking.facade.user.reservation.RefundReservationFacade;
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
    private final FailReservationFacade failReservationFacade;

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createReservation(CompletePaymentEventDto completePaymentEventDto) {
        createReservationFacade.execute(completePaymentEventDto);
    }

    // todo 제거
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void cancelReservation(RefundPaymentEventDto refundPaymentEventDto) {
        refundReservationFacade.execute(refundPaymentEventDto);
    }

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void failReservation(FailedPaymentEventDto failedPaymentEventDto) {
        failReservationFacade.execute(failedPaymentEventDto.trackingId(), failedPaymentEventDto.memberId());
    }
}
