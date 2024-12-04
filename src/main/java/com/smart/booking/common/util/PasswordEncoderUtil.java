package com.smart.booking.common.util;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderUtil {

    private final PasswordEncoder passwordEncoder;

    // 비밀번호 암호화
    public String encodePassword(@NonNull String password) {
        return passwordEncoder.encode(password);
    }

    // 비밀번호 일치 여부 확인
    public boolean matches(@NonNull String rawPassword, @NonNull String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}

