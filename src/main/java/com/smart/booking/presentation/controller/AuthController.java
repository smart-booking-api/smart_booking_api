package com.smart.booking.presentation.controller;

import com.smart.booking.domain.auth.entity.RefreshToken;
import com.smart.booking.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/refresh")
    public String refreshToken(@RequestBody String refreshToken) {
        if (authService.isExpiredRefreshToken(refreshToken)) {
            return "Refresh token is expired";
        }

        RefreshToken findToken = authService.findByRefreshToken(refreshToken);

        return authService.createAccessToken(findToken.getMember().getId(), findToken.getMember().getType().getKey());
    }

    @PostMapping("/third-party")
    public String thirdPartyLogin(@RequestBody String providerUserId) {
        return authService.createAccessTokenByProviderUserId(providerUserId);
    }
}
