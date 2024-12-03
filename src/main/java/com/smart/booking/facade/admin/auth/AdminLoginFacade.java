package com.smart.booking.facade.admin.auth;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.common.util.PasswordUtil;
import com.smart.booking.domain.auth.service.AuthService;
import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.facade.dto.auth.AdminLogin;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminLoginFacade {
    private final AuthService authService;
    private final PartnerService partnerService;
    private final PasswordUtil passwordUtil;

    public AdminLogin.loginResponse execute(AdminLogin.loginRequest loginRequest) {
        Partner partner = getByLoginId(loginRequest.getLoginId()).orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_USER));

        if (!matchPassword(loginRequest.getPassword(),partner.getPassword())) {
            throw new CommonException(ResponseCode.NOT_MATCHED_PASSWORD);
        }

        String accessToken = authService.createAccessToken(partner.getMember().getId(), partner.getMember().getType().getKey());
        String refreshToken = authService.createRefreshToken(partner.getMember().getId());

        return new AdminLogin.loginResponse(accessToken, refreshToken);
    }

    private Optional<Partner> getByLoginId(String loginId) {
        return partnerService.getPartnerByLoginId(loginId);
    }

    private boolean matchPassword(String userPassword, String loginPassword) {
        return passwordUtil.matches(userPassword, loginPassword);
    }
}
