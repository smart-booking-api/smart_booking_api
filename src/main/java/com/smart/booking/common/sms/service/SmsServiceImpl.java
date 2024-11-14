package com.smart.booking.common.sms.service;

import com.smart.booking.common.sms.external.SmsExternalService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SmsServiceImpl implements SmsService {

    private final SmsExternalService smsExternalService;

    public void sendSms(@NonNull String phoneNumber, @NonNull String message) throws Exception {
        smsExternalService.sendSms(phoneNumber, message);
    }

}
