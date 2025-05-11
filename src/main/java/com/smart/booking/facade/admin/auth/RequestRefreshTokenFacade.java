package com.smart.booking.facade.admin.auth;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.auth.entity.RefreshToken;
import com.smart.booking.domain.auth.service.AuthService;
import com.smart.booking.facade.dto.auth.RequestRefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestRefreshTokenFacade {

    private final AuthService authService;

    public RequestRefreshToken.responseToken execute(RequestRefreshToken.requestToken requestToken) {
        if (authService.isExpiredRefreshToken(requestToken.getRefreshToken())) {
            throw new CommonException(ResponseCode.NOT_VALID_REFRESH_TOKEN);
        }

        RefreshToken findToken = authService.findByRefreshToken(requestToken.getRefreshToken());

        String accessToken = authService.createAccessToken(findToken.getMember().getId(), findToken.getMember().getType().getKey());

        return new RequestRefreshToken.responseToken(accessToken);
    }

}
