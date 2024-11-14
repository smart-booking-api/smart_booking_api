package com.smart.booking.domain.auth.service;

import com.smart.booking.domain.auth.entity.PhoneNumberToken;
import com.smart.booking.domain.auth.entity.RefreshToken;
import com.smart.booking.domain.auth.repository.PhoneNumberTokenRepository;
import com.smart.booking.domain.auth.repository.RefreshTokenRepository;
import com.smart.booking.domain.auth.value_object.Token;
import com.smart.booking.domain.member.entity.Member;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final PhoneNumberTokenRepository phoneNumberTokenRepository;

    @Override
    public @NonNull Token reissueToken(@NonNull String refreshToken) {
        return null;
    }

    @Override
    public @NonNull Token createToken(@NonNull String memberId) {
        return null;
    }

    @Override
    public RefreshToken getRefreshTokenByMember(@NonNull Member member) {
        return refreshTokenRepository.findByMember(member);
    }

    @Override
    public RefreshToken createRefreshToken(@NonNull Member member, String token) {
        RefreshToken refreshToken = RefreshToken.builder()
            .member(member)
            .token(token)
            .expiredAt(LocalDateTime.now().plusDays(7))
            .valid(true).build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken getRefreshTokenByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken);
    }

    @Override
    public @NonNull PhoneNumberToken createPhoneNumberToken(@NonNull String phoneNumber) {

        final String code = IntStream.range(0, 6)
            .mapToObj(i -> String.valueOf((int) (Math.random() * 10)))
            .collect(Collectors.joining());

        return phoneNumberTokenRepository.save(
            PhoneNumberToken.builder()
                .code(code)
                .phoneNumber(phoneNumber)
                .build()
        );
    }
}
