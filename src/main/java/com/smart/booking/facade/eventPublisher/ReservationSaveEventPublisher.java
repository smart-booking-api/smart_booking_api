package com.smart.booking.facade.eventPublisher;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationSaveEventPublisher {
    private final ApplicationEventPublisher publisher;

    public void publish(String paymentId) throws Exception {
        publisher.publishEvent(paymentId);
    }
}
