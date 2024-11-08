package com.smart.booking.facade.event.publisher;

import com.smart.booking.facade.dto.payment.CompletePaymentRequestDto;
import com.smart.booking.facade.event.dto.CompletePaymentEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompletePaymentEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publish(CompletePaymentEventDto completePaymentEventDto) {
        publisher.publishEvent(completePaymentEventDto);
    }
}
