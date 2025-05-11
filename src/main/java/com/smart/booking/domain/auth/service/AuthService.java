package com.smart.booking.domain.auth.service;

import com.smart.booking.domain.auth.entity.RefreshToken;
import com.smart.booking.domain.auth.value_object.Token;
import com.smart.booking.domain.auth.value_object.UserPhoneAuth;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import lombok.NonNull;

public interface AuthService {

    String getMemberIdFromToken(String token);

    Boolean isExpiredToken(String token);

    Boolean isExpiredRefreshToken(String token);

    void updateRefreshToken(String memberId, String refreshToken);

    @NonNull
    String createAccessToken(@NonNull String memberId, @NonNull String role);

    @NonNull
    RefreshToken createRefreshToken(@NonNull Member member);

    RefreshToken findByRefreshToken(String refreshToken);


    void deleteRefreshTokenByMember(@NonNull Member member);

    @NonNull
    UserPhoneAuth getPhoneAuthCode(@NonNull String phoneNumber);

    @NonNull
    UserPhoneAuth createPhoneAuthCode(@NonNull String phoneNumber);

    void deletePhoneAuthCode(@NonNull String phoneNumber);

    @NonNull
    Token generateToken(@NonNull String memberId, @NonNull MemberType role);

}
