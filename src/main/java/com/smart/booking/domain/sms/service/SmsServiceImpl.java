package com.smart.booking.domain.sms.service;

import com.smart.booking.domain.sms.entity.Sms;
import com.smart.booking.domain.sms.repository.SmsRepository;
import com.smart.booking.external.sms.ExternalSmsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SmsServiceImpl implements SmsService {

    private final SmsRepository smsRepository;
    private final ExternalSmsService externalSmsService;

    @Override
    public @NonNull Sms sendSms(String phoneNumber, String message) {
        final Sms sms = Sms.builder()
            .phoneNumber(phoneNumber)
            .message(message)
            .build();

        try {
            externalSmsService.sendSms(sms.getId(), phoneNumber, message);
            return smsRepository.save(sms);
        } catch (Exception e) {
            sms.occurredError(e.getMessage());
            return smsRepository.save(sms);
        }
    }

}
