package com.smart.booking.common.sms.service;

import lombok.NonNull;

public interface SmsService {

    void sendSms(@NonNull String phoneNumber, @NonNull String message) throws Exception;
}
