package com.smart.booking.external.sms;

import lombok.NonNull;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExternalCoolSmsServiceImpl implements ExternalSmsService {

    private final DefaultMessageService defaultMessageService;
    private final String fromNumber;

    ExternalCoolSmsServiceImpl(
        @Value("${external.sms.key}") String apiKey,
        @Value("${external.sms.secret}") String apiSecret,
        @Value("${external.sms.endpoint}") String apiEndpoint,
        @Value("${external.sms.from}") String fromNumber
    ) {
        this.defaultMessageService = NurigoApp.INSTANCE.initialize(
            apiKey,
            apiSecret,
            apiEndpoint
        );

        this.fromNumber = fromNumber;
    }

    @Override
    public void sendSms(@NonNull String id, @NonNull String phoneNumber, @NonNull String message)
        throws NurigoMessageNotReceivedException, NurigoEmptyResponseException, NurigoUnknownException {

        final Message msg = new Message();

        msg.setMessageId(id);
        msg.setFrom(this.fromNumber);
        msg.setTo(phoneNumber);
        msg.setText(message);

        defaultMessageService.send(msg);
    }
}
