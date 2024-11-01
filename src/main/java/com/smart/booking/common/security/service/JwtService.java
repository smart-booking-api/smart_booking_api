package com.smart.booking.common.security.service;

import com.smart.booking.domain.auth.entity.RefreshToken;
import com.smart.booking.domain.auth.service.AuthService;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private SecretKey secretKey;
    private final MemberService memberService;
    private final AuthService authService;

    public JwtService(@Value("${spring.jwt.secret}") String secret, MemberService memberService, AuthService authService) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), SIG.HS256.key().build().getAlgorithm());
        this.memberService = memberService;
        this.authService = authService;
    }

    public String getUserId(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userId", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public Boolean isRefreshExpired(String token) {
        Date current = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(Calendar.DATE, 3);

        Date after7dayFromToday = calendar.getTime();

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(after7dayFromToday);
    }

    public void createRefreshToken(String userId, String refreshToken) {
        Member member = memberService.getMember(userId);
        authService.createRefreshToken(member, refreshToken);
    }

    public void updateRefreshToken(String userId, String refreshToken) {
        Member member = memberService.getMember(userId);
        RefreshToken oldToken = authService.getRefreshTokenByMember(member);
        oldToken.updateToken(refreshToken);
    }

    public String createAccessToken(String userId, String role, Long expiredMs) {
        return Jwts.builder()
            .claim("userId", userId)
            .claim("role", role)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + expiredMs))
            .signWith(secretKey)
            .compact();
    }

    public String createRefreshToken(String userId) {
        return Jwts.builder()
            .claim("userId", userId)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 1*(1000*60*60*24*7)))
            .signWith(secretKey)
            .compact();
    }

    public RefreshToken findByRefreshToken(String refreshToken) {
        return authService.getRefreshTokenByRefreshToken(refreshToken);
    }
}
