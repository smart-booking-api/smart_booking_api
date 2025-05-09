package com.smart.booking.data.auth;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.auth.repository.UserPhoneAuthRepository;
import com.smart.booking.domain.auth.value_object.UserPhoneAuth;
import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPhoneAuthCacheRepository implements UserPhoneAuthRepository {

    private final RedisTemplate<String, String> redisTemplate;

    private final String USER_PHONE_AUTH_PREFIX = "user:phone_auth:";


    @Override
    public @NonNull UserPhoneAuth findByPhoneNumberOrThrow(@NonNull String phoneNumber) {

        final var authCode = redisTemplate.opsForValue().get(this.USER_PHONE_AUTH_PREFIX + phoneNumber);

        if (authCode == null) {
            throw new CommonException(ResponseCode.NOT_FOUND_PHONE_AUTH_CODE);
        }

        return new UserPhoneAuth(
            phoneNumber,
            redisTemplate.opsForValue().get(this.USER_PHONE_AUTH_PREFIX + phoneNumber),
            OffsetDateTime.now().plusSeconds(redisTemplate.getExpire(this.USER_PHONE_AUTH_PREFIX + phoneNumber, TimeUnit.SECONDS))
        );
    }

    @Override
    public @NonNull UserPhoneAuth create(@NonNull String phoneNumber, @NonNull String authCode, int minutes) {
        redisTemplate.opsForValue().set(this.USER_PHONE_AUTH_PREFIX + phoneNumber, authCode, minutes, TimeUnit.MINUTES);

        return new UserPhoneAuth(
            phoneNumber,
            authCode,
            OffsetDateTime.now().plusMinutes(minutes)
        );
    }

    @Override
    public void deleteByPhoneNumber(@NonNull String phoneNumber) {
        redisTemplate.delete(this.USER_PHONE_AUTH_PREFIX + phoneNumber);
    }
}
