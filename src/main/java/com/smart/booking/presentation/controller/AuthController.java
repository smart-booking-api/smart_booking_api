package com.smart.booking.presentation.controller;

import com.smart.booking.domain.auth.service.AuthService;
import com.smart.booking.facade.admin.auth.AdminLoginFacade;
import com.smart.booking.facade.admin.auth.RequestRefreshTokenFacade;
import com.smart.booking.facade.admin.auth.ThirdPartyLoginFacade;
import com.smart.booking.facade.dto.auth.AdminLogin;
import com.smart.booking.facade.dto.auth.RequestRefreshToken;
import com.smart.booking.facade.dto.auth.ThirdPartyLogin;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AdminLoginFacade adminLoginFacade;
    private final ThirdPartyLoginFacade thirdPartyLoginFacade;
    private final RequestRefreshTokenFacade requestRefreshTokenFacade;

    @PostMapping("/refresh")
    public RequestRefreshToken.responseToken refreshToken(@RequestBody RequestRefreshToken.requestToken requestToken) {
        return requestRefreshTokenFacade.execute(requestToken);
    }

    @PostMapping("/third-party")
    public ThirdPartyLogin.thirdLoginResponse thirdPartyLogin(@RequestBody @Valid ThirdPartyLogin.thirdLoginRequest thirdLoginRequest) {
        return thirdPartyLoginFacade.execute(thirdLoginRequest);
    }

    @PostMapping("/login")
    public AdminLogin.loginResponse login(@RequestBody @Valid AdminLogin.loginRequest loginRequest) {
        return adminLoginFacade.execute(loginRequest);
    }
}
