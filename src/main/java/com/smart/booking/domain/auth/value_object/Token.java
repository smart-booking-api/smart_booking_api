package com.smart.booking.domain.auth.value_object;

import lombok.NonNull;

public record Token(
    @NonNull String accessToken,
    @NonNull String refreshToken
) {

   
}
