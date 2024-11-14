package com.smart.booking.domain.auth.service;

import com.smart.booking.domain.auth.entity.PhoneNumberToken;
import com.smart.booking.domain.auth.entity.RefreshToken;
import com.smart.booking.domain.auth.value_object.Token;
import com.smart.booking.domain.member.entity.Member;
import lombok.NonNull;

public interface AuthService {

    @NonNull Token reissueToken(@NonNull String refreshToken);

    @NonNull Token createToken(@NonNull String memberId);

    RefreshToken getRefreshTokenByMember(@NonNull Member member);

    RefreshToken createRefreshToken(@NonNull Member member, String token);

    RefreshToken getRefreshTokenByRefreshToken(String refreshToken);

    @NonNull PhoneNumberToken createPhoneNumberToken(@NonNull String phoneNumber);

}
