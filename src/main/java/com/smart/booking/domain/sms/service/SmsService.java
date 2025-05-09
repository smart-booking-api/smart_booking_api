package com.smart.booking.domain.sms.service;

import com.smart.booking.domain.sms.entity.Sms;
import lombok.NonNull;

public interface SmsService {

    @NonNull
    Sms sendSms(@NonNull String phoneNumber, @NonNull String message);

}
