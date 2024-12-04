package com.smart.booking.facade.common.sms;

import com.smart.booking.common.dto.CommonEmptyResponse;
import com.smart.booking.domain.sms.service.SmsService;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SendSmsFacade {

    private final SmsService smsService;

    @Transactional
    public SendSmsResponse execute(@NonNull SendSmsRequestDto sendSmsRequestDto) {
        smsService.sendSms(sendSmsRequestDto.getPhoneNumber(), sendSmsRequestDto.getMessage());

        return new SendSmsResponse();
    }

    @Getter
    @RequiredArgsConstructor
    public static class SendSmsRequestDto {

        private final String phoneNumber;
        private final String message;
    }

    public static class SendSmsResponse extends CommonEmptyResponse {

    }
}
