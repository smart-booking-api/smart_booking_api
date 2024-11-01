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

    @NonNull Token reissueToken(@NonNull String refreshToken);

    @NonNull Token createToken(@NonNull String memberId);

    void withdrawUser(@NonNull String userId);

    void withdrawPartner(@NonNull String partnerId);

    RefreshToken getRefreshTokenByMember(@NonNull Member member);

    RefreshToken createRefreshToken(@NonNull Member member, String token);

    RefreshToken getRefreshTokenByRefreshToken(String refreshToken);
}
