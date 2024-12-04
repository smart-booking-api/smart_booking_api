package com.smart.booking.facade.event.publisher;

import com.smart.booking.facade.event.dto.CreatePartnerSuccessEvent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartnerEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publishCreatePartnerSuccessEvent(@NonNull CreatePartnerSuccessEvent createPartnerSuccessEvent) {
        publisher.publishEvent(createPartnerSuccessEvent);
    }
}
