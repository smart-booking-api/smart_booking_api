package com.smart.booking.domain.auth.service;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.auth.entity.RefreshToken;
import com.smart.booking.domain.auth.repository.RefreshTokenRepository;
import com.smart.booking.domain.auth.repository.UserPhoneAuthRepository;
import com.smart.booking.domain.auth.value_object.UserPhoneAuth;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import com.smart.booking.domain.user.service.UserUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final long EXPIRATION_TIME_MS = 60 * 60 * 10 * 1000L;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberService memberService;
    private final UserUserService userService;
    private SecretKey secretKey;
    @Value("${spring.jwt.secret}")
    private String secretString;

    private UserPhoneAuthRepository userPhoneAuthRepository;

    @PostConstruct
    public void init() {
        this.secretKey = new SecretKeySpec(secretString.getBytes(StandardCharsets.UTF_8), SIG.HS256.key().build().getAlgorithm());
    }

    @Override
    public String getMemberIdFromToken(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("memberId", String.class);
    }

    @Override
    public Boolean isExpiredToken(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    @Override
    public Boolean isExpiredRefreshToken(String token) {
        Date current = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(Calendar.DATE, 3);

        Date after7dayFromToday = calendar.getTime();

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(after7dayFromToday);
    }

    @Override
    public void updateRefreshToken(String memberId, String refreshToken) {
        Member member = memberService.getMemberById(memberId);
        RefreshToken oldToken = getRefreshTokenByMember(member);
        oldToken.updateToken(refreshToken);
    }

    @Override
    public String createAccessToken(String memberId, String role) {
        Date now = new Date();
        return Jwts.builder()
            .claim("memberId", memberId)
            .claim("role", role)
            .issuedAt(now)
            .expiration(new Date(now.getTime() + EXPIRATION_TIME_MS))
            .signWith(secretKey)
            .compact();
    }

    @Override
    public @NonNull RefreshToken createRefreshToken(@NonNull Member member) {
        final var expiredAt = OffsetDateTime.now().plusDays(7);

        final String token = Jwts.builder()
            .claim("memberId", member.getId())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(Date.from(expiredAt.toInstant()))
            .signWith(secretKey)
            .compact();

        return refreshTokenRepository.save(
            RefreshToken.builder()
                .token(token)
                .member(member)
                .expiredAt(expiredAt)
                .build()
        );
    }

    @Override
    public RefreshToken findByRefreshToken(String refreshToken) {
        return getRefreshTokenByRefreshToken(refreshToken);
    }

    @Override
    public Member getMemberByProviderUserIdAndProvider(String providerUserId, ThirdPartyAccountProvider provider) {
        User user = userService.getByProviderUserIdAndProvider(providerUserId, provider)
            .orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_THIRD_PARTY_ACCOUNT));
        return user.getMember();
    }

    @Override
    public void deleteRefreshTokenByMember(@NonNull Member member) {
        refreshTokenRepository.deleteByMember(member);
    }

    @Override
    public @NonNull UserPhoneAuth getPhoneAuthCode(@NonNull String phoneNumber) {
        return userPhoneAuthRepository.findByPhoneNumberOrThrow(phoneNumber);
    }

    @Override
    public @NonNull UserPhoneAuth createPhoneAuthCode(@NonNull String phoneNumber) {
        final var randomCode = (int) (Math.random() * 1000000);

        // 6자리로 패딩 (자릿수 부족시 0으로 채움)
        final var code = String.format("%06d", randomCode);

        return userPhoneAuthRepository.create(phoneNumber, code, 3);
    }

    @Override
    public void deletePhoneAuthCode(@NonNull String phoneNumber) {
        userPhoneAuthRepository.deleteByPhoneNumber(phoneNumber);
    }


    private RefreshToken getRefreshTokenByMember(@NonNull Member member) {
        return refreshTokenRepository.findByMember(member);
    }

    private RefreshToken getRefreshTokenByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken);
    }
}
