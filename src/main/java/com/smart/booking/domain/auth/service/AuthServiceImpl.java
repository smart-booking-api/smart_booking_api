package com.smart.booking.domain.auth.service;

import com.smart.booking.domain.auth.entity.RefreshToken;
import com.smart.booking.domain.auth.repository.RefreshTokenRepository;
import com.smart.booking.domain.auth.value_object.Token;
import com.smart.booking.domain.auth.value_object.UserSignInDto;
import com.smart.booking.domain.member.entity.Member;
import java.time.LocalDateTime;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public @NonNull Token signInUser(@NonNull UserSignInDto userSignInDto) {
        return null;
    }

    @Override
    public void signUpUser() {

    }

    @Override
    public @NonNull Token signInPartner() {
        return null;
    }

    @Override
    public void signOutUser() {

    }

    @Override
    public void signOutPartner() {

    }

    @Override
    public @NonNull Token reissueToken(@NonNull String refreshToken) {
        return null;
    }

    @Override
    public @NonNull Token createToken(@NonNull String memberId) {
        return null;
    }

    @Override
    public void withdrawUser(@NonNull String userId) {

    }

    @Override
    public void withdrawPartner(@NonNull String partnerId) {

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
}
