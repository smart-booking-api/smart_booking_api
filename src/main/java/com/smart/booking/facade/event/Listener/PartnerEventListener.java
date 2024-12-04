package com.smart.booking.facade.event.listener;

import com.smart.booking.facade.common.sms.SendSmsFacade;
import com.smart.booking.facade.common.sms.SendSmsFacade.SendSmsRequestDto;
import com.smart.booking.facade.event.dto.CreatePartnerSuccessEvent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class PartnerEventListener {

    private final SendSmsFacade sendSmsFacade;

    @Async
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onCreatePartnerSuccessEvent(@NonNull CreatePartnerSuccessEvent createPartnerSuccessEvent) throws Exception {
        final SendSmsRequestDto sendSmsRequestDto = new SendSmsRequestDto(
            createPartnerSuccessEvent.getPhoneNumber(),
            createPartnerSuccessEvent.getMessage()
        );

        sendSmsFacade.execute(sendSmsRequestDto);
    }

}
