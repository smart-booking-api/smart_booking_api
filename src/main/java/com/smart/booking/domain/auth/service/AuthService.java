package com.smart.booking.domain.auth.service;

import com.smart.booking.domain.auth.entity.RefreshToken;
import com.smart.booking.domain.auth.value_object.Token;
import com.smart.booking.domain.auth.value_object.UserSignInDto;
import com.smart.booking.domain.member.entity.Member;
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

    //RefreshToken getRefreshTokenByMember(@NonNull Member member);

    // todo 제거
    // RefreshToken getRefreshTokenByRefreshToken(String refreshToken);

    String getUserIdFromToken(String token);

    Boolean isExpiredToken(String token);

    Boolean isExpiredRefreshToken(String token);

    RefreshToken createRefreshTokenByUserId(String userId, String refreshToken);

    void updateRefreshToken(String userId, String refreshToken);

    String createAccessToken(String userId, String role);

    String createRefreshToken(String userId);

    RefreshToken findByRefreshToken(String refreshToken);
}
