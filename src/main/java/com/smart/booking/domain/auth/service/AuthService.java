package com.smart.booking.domain.auth.service;

import com.smart.booking.domain.auth.entity.RefreshToken;
import com.smart.booking.domain.auth.value_object.Token;
import com.smart.booking.domain.auth.value_object.UserSignInDto;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import lombok.NonNull;

public interface AuthService {

    @NonNull Token signInUser(@NonNull UserSignInDto userSignInDto);

    void signUpUser();

    @NonNull Token signInPartner();

    void signOutUser();

    void signOutPartner();

    // todo 제거
    @NonNull Token reissueToken(@NonNull String refreshToken);

    // todo 제거
    @NonNull Token createToken(@NonNull String memberId);

    void withdrawUser(@NonNull String userId);

    void withdrawPartner(@NonNull String partnerId);

    String getMemberIdFromToken(String token);

    Boolean isExpiredToken(String token);

    Boolean isExpiredRefreshToken(String token);

    RefreshToken createRefreshTokenByUserId(String memberId, String refreshToken);

    void updateRefreshToken(String memberId, String refreshToken);

    String createAccessToken(String memberId, String role);

    String createRefreshToken(String memberId);

    RefreshToken findByRefreshToken(String refreshToken);

    Member getMemberByProviderUserIdAndProvider(String providerUserId, ThirdPartyAccountProvider provider);
}
