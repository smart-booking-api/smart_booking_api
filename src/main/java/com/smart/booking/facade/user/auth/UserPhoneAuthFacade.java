package com.smart.booking.facade.user.auth;

import com.smart.booking.common.dto.CommonEmptyResponse;
import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.auth.service.AuthService;
import com.smart.booking.domain.sms.service.SmsService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserPhoneAuthFacade {

    private final AuthService authService;
    private final SmsService smsService;

    @Transactional
    public SendPhoneAuthCodeResponse sendPhoneAuthCode(@NonNull SendPhoneAuthCodeRequest request) {
        final var phoneAuthCode = this.authService.createPhoneAuthCode(request.phoneNumber);

        final var sms = this.smsService.sendSms(phoneAuthCode.phoneNumber(), "인증번호는 :" + phoneAuthCode.authCode() + "입니다.");

        return new SendPhoneAuthCodeResponse();
    }

    @Transactional
    public VerifyPhoneAuthCodeResponse verifyPhoneAuthCode(@NonNull VerifyPhoneAuthCodeRequest request) {

        final var phoneAuthCode = this.authService.getPhoneAuthCode(request.phoneNumber);

        if (!phoneAuthCode.validate(request.authCode)) {
            throw new CommonException(ResponseCode.NOT_MATCHED_PHONE_AUTH_CODE);
        }

        this.authService.deletePhoneAuthCode(request.phoneNumber);

        return new VerifyPhoneAuthCodeResponse();
    }

    public static class SendPhoneAuthCodeRequest {

        @NotBlank(message = "로그인 패스워드를 입력해주세요.")
        @Schema(description = "전화번호")
        public String phoneNumber;

    }

    public static class SendPhoneAuthCodeResponse extends CommonEmptyResponse {

    }


    public static class VerifyPhoneAuthCodeRequest {


        @NotBlank(message = "로그인 패스워드를 입력해주세요.")
        @Schema(description = "전화번호")
        public String phoneNumber;

        @NotNull
        @Schema(description = "인증번호")
        public String authCode;

    }

    public static class VerifyPhoneAuthCodeResponse extends CommonEmptyResponse {

    }


}
