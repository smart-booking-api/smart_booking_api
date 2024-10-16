package com.smart.booking.domain.member.enums;

import com.smart.booking.common.enums.EnumModel;
import lombok.NonNull;

public enum MemberType implements EnumModel {
    USER("사용자"),
    PARTNER("파트너"),
    ;

    private final @NonNull String value;

    MemberType(@NonNull String value) {
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
