package com.smart.booking.domain.auth.value_object;

import java.time.OffsetDateTime;
import lombok.NonNull;

public record UserPhoneAuth(
    @NonNull String phoneNumber,
    @NonNull String authCode,
    @NonNull OffsetDateTime expiredAt
) {

    public boolean isExpired() {
        return OffsetDateTime.now().isAfter(expiredAt);
    }

    public boolean validate(String authCode) {
        return this.authCode.equals(authCode) && !isExpired();
    }

}


