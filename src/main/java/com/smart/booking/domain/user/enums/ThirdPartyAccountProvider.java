package com.smart.booking.domain.user.enums;

import com.smart.booking.common.enums.EnumModel;
import lombok.NonNull;

public enum ThirdPartyAccountProvider implements EnumModel {
    KAKAO("카카오"),
    NAVER("네이버"),
    APPLE("애플"),
    ;

    private final @NonNull String value;

    ThirdPartyAccountProvider(@NonNull String value) {
        this.value = value;
    }

    @Override
    public @NonNull String getKey() {
        return name();
    }

    @Override
    public @NonNull String getValue() {
        return value;
    }
}
