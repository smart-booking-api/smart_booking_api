package com.smart.booking.external.sms;

import lombok.NonNull;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;

public interface ExternalSmsService {

    void sendSms(
        @NonNull String id,
        @NonNull String phoneNumber,
        @NonNull String message
    )
        throws NurigoMessageNotReceivedException, NurigoEmptyResponseException, NurigoUnknownException;


}
