package com.smart.booking.common.sms.external;

import lombok.NonNull;

public interface SmsExternalService {

    void sendSms(@NonNull String phoneNumber, @NonNull String message)
        throws Exception;

}
