package com.smart.booking.facade.event.publisher;

import com.smart.booking.facade.dto.payment.CompletePaymentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompletePaymentEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publish(CompletePaymentRequestDto completePaymentRequestDto) throws Exception {
        publisher.publishEvent(completePaymentRequestDto);
    }
}
