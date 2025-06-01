package com.smart.booking.facade.event.publisher;

import com.smart.booking.facade.event.dto.CompletePaymentEventDto;
import com.smart.booking.facade.event.dto.FailPaymentEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FailPaymentEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publish(FailPaymentEventDto failPaymentEventDto) {
        publisher.publishEvent(failPaymentEventDto);
    }
}
