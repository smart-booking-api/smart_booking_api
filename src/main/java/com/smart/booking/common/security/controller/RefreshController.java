package com.smart.booking.common.security.controller;

import com.smart.booking.common.security.service.JwtService;
import com.smart.booking.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RefreshController {
    private final JwtService jwtService;

    @PostMapping("/refresh")
    public String refreshToken(@RequestBody String refreshToken) {
        if (jwtService.isRefreshExpired(refreshToken)) {
            return "Refresh token is expired";
        }

        User user = jwtService.findByRefreshToken(refreshToken);

        return jwtService.createAccessToken(user.getEmail(), user.getRole(), 60*60*10L);
    }
}
