package com.smart.booking.common.sms.external;

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
class CoolSmsExternalService implements SmsExternalService {

    private final DefaultMessageService defaultMessageService;
    private final String fromNumber;

    CoolSmsExternalService(
        @Value("${sms.key}") String apiKey,
        @Value("${sms.secret}") String apiSecret,
        @Value("${sms.endpoint}") String apiEndpoint,
        @Value("${sms.from}") String fromNumber
    ) {
        this.defaultMessageService = NurigoApp.INSTANCE.initialize(
            apiKey,
            apiSecret,
            apiEndpoint
        );

        this.fromNumber = fromNumber;
    }


    @Override
    public void sendSms(@NonNull String phoneNumber, @NonNull String message)
        throws NurigoMessageNotReceivedException, NurigoEmptyResponseException, NurigoUnknownException {
        final Message msg = new Message();

        msg.setFrom(this.fromNumber);
        msg.setTo(phoneNumber);
        msg.setText(message);

        defaultMessageService.send(msg);
    }
}
