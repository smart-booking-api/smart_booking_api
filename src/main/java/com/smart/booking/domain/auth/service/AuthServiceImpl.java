package com.smart.booking.domain.auth.service;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.auth.entity.RefreshToken;
import com.smart.booking.domain.auth.repository.RefreshTokenRepository;
import com.smart.booking.domain.auth.value_object.Token;
import com.smart.booking.domain.auth.value_object.UserSignInDto;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.service.UserUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
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
    private SecretKey secretKey;
    @Value("${spring.jwt.secret}")
    private String secretString;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberService memberService;
    private final UserUserService userService;
    private static final long EXPIRATION_TIME_MS = 60 * 60 * 10 * 1000L;

    @PostConstruct
    public void init() {
        this.secretKey = new SecretKeySpec(secretString.getBytes(StandardCharsets.UTF_8), SIG.HS256.key().build().getAlgorithm());
    }

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
    public RefreshToken createRefreshTokenByUserId(String memberId, String token) {
        Member member = memberService.getMemberById(memberId);

        RefreshToken refreshToken = RefreshToken.builder()
            .member(member)
            .token(token)
            .expiredAt(LocalDateTime.now().plusDays(7))
            .valid(true).build();
        return refreshTokenRepository.save(refreshToken);
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
    public String createRefreshToken(String memberId) {
        return Jwts.builder()
            .claim("memberId", memberId)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 1*(1000*60*60*24*7)))
            .signWith(secretKey)
            .compact();
    }

    @Override
    public RefreshToken findByRefreshToken(String refreshToken) {
        return getRefreshTokenByRefreshToken(refreshToken);
    }

    @Override
    public String createAccessTokenByProviderUserId(String providerUserId) {
        Member member = getMemberByProviderUserId(providerUserId);
        return createRefreshToken(member.getId());
    }

    private Member getMemberByProviderUserId(String providerUserId) {
        User user = userService.getByProviderUserId(providerUserId).orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_THIRD_PARTY_ACCOUNT));
        return user.getMember();
    }


    private RefreshToken getRefreshTokenByMember(@NonNull Member member) {
        return refreshTokenRepository.findByMember(member);
    }

    private RefreshToken getRefreshTokenByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken);
    }
}
