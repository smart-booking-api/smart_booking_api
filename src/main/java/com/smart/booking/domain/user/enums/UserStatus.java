package com.smart.booking.domain.user.enums;

import com.smart.booking.common.enums.EnumModel;
import lombok.NonNull;

public enum UserStatus implements EnumModel {
    ACTIVE("활성"),
    DORMANCY("휴면"),
    WITHDRAWAL("탈퇴"),
    ;

    private final String value;

    UserStatus(@NonNull String value) {
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
