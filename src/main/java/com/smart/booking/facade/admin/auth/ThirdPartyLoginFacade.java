package com.smart.booking.facade.admin.auth;

import com.smart.booking.domain.auth.service.AuthService;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.facade.dto.auth.ThirdPartyLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ThirdPartyLoginFacade {

    private final AuthService authService;

    public ThirdPartyLogin.thirdLoginResponse execute(ThirdPartyLogin.thirdLoginRequest thirdLoginRequest) {
        Member member = authService.getMemberByProviderUserIdAndProvider(thirdLoginRequest.getProviderUserId(), thirdLoginRequest.getProvider());

        String accessToken = authService.createAccessToken(member.getId(), member.getType().getKey());
        String refreshToken = authService.createRefreshToken(member).getToken();

        return new ThirdPartyLogin.thirdLoginResponse(accessToken, refreshToken);
    }

}
