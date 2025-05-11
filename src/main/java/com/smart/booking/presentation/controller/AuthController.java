package com.smart.booking.presentation.controller;

import com.smart.booking.facade.admin.auth.AdminLoginFacade;
import com.smart.booking.facade.admin.auth.RequestRefreshTokenFacade;
import com.smart.booking.facade.dto.auth.AdminLogin;
import com.smart.booking.facade.dto.auth.RequestRefreshToken;
import com.smart.booking.facade.user.auth.UserAuthFacade;
import com.smart.booking.facade.user.auth.UserAuthFacade.SignUpUserResponse;
import com.smart.booking.facade.user.auth.UserPhoneAuthFacade;
import com.smart.booking.presentation.controller.endPoint.AuthEndPoint;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "인증", description = "인증 컨트롤러")
public class AuthController {

    private final AdminLoginFacade adminLoginFacade;
    private final RequestRefreshTokenFacade requestRefreshTokenFacade;
    private final UserPhoneAuthFacade userPhoneAuthFacade;
    private final UserAuthFacade userAuthFacade;


    @PostMapping(AuthEndPoint.REFRESH_TOKEN)
    public RequestRefreshToken.responseToken refreshToken(@RequestBody RequestRefreshToken.requestToken requestToken) {
        return requestRefreshTokenFacade.execute(requestToken);
    }

    @PostMapping(AuthEndPoint.THIRD_PARTY_LOGIN)
    public UserAuthFacade.SignInUserResponse thirdPartyLogin(@RequestBody @Valid UserAuthFacade.SignInUserRequest request) {
        return userAuthFacade.signIn(request);
    }

    @PostMapping(AuthEndPoint.LOGIN)
    public AdminLogin.loginResponse login(@RequestBody @Valid AdminLogin.loginRequest loginRequest) {
        return adminLoginFacade.execute(loginRequest);
    }

    @PostMapping(AuthEndPoint.PHONE_AUTH)
    public UserPhoneAuthFacade.SendPhoneAuthCodeResponse phoneAuth(
        @RequestBody @Valid UserPhoneAuthFacade.SendPhoneAuthCodeRequest request
    ) {
        return userPhoneAuthFacade.sendPhoneAuthCode(request);
    }

    @PostMapping(AuthEndPoint.PHONE_AUTH_VERIFY)
    public UserPhoneAuthFacade.VerifyPhoneAuthCodeResponse phoneAuthVerify(
        @RequestBody @Valid UserPhoneAuthFacade.VerifyPhoneAuthCodeRequest request
    ) {
        return userPhoneAuthFacade.verifyPhoneAuthCode(request);
    }


    @PostMapping(AuthEndPoint.SIGN_UP)
    public SignUpUserResponse signUp(
        @RequestBody @Valid UserAuthFacade.SignUpUserRequest request
    ) {
        return userAuthFacade.signUp(request);
    }
}
