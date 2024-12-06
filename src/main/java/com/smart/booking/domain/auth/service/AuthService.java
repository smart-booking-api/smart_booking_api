package com.smart.booking.domain.auth.service;

import com.smart.booking.domain.auth.entity.RefreshToken;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import lombok.NonNull;

public interface AuthService {

    String getMemberIdFromToken(String token);

    Boolean isExpiredToken(String token);

    Boolean isExpiredRefreshToken(String token);

    void updateRefreshToken(String memberId, String refreshToken);

    String createAccessToken(String memberId, String role);

    @NonNull RefreshToken createRefreshToken(@NonNull Member member);

    RefreshToken findByRefreshToken(String refreshToken);

    Member getMemberByProviderUserIdAndProvider(String providerUserId, ThirdPartyAccountProvider provider);

    void deleteRefreshTokenByMember(@NonNull Member member);
}
