package com.smart.booking.common.security.service;

import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.repository.UserRepository;
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
    private final UserRepository userRepository;

    public JwtService(@Value("${spring.jwt.secret}") String secret, UserRepository userRepository) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), SIG.HS256.key().build().getAlgorithm());
        this.userRepository = userRepository;
    }

    public String getEmail(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("email", String.class);
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
        calendar.add(Calendar.DATE, 7);

        Date after7dayFromToday = calendar.getTime();

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(after7dayFromToday);
    }

    public void setRefreshToken(String email, String refreshToken) {
        userRepository.findByEmail(email)
            .ifPresent(user -> user.setRefreshToken(refreshToken));
    }

    public String createAccessToken(String email, String role, Long expiredMs) {
        return Jwts.builder()
            .claim("role", role)
            .claim("email", email)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + expiredMs))
            .signWith(secretKey)
            .compact();
    }

    public String createRefreshToken(String email) {
        return Jwts.builder()
            .claim("email", email)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 1*(1000*60*60*24*7)))
            .signWith(secretKey)
            .compact();
    }

    public User findByRefreshToken(String refreshToken) {
        return userRepository.findByRefreshToken(refreshToken)
            .orElse(null);
    }
}
