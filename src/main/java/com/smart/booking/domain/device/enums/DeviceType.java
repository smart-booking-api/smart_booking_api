package com.smart.booking.domain.device.enums;

import com.smart.booking.common.enums.EnumModel;
import lombok.NonNull;

public enum DeviceType implements EnumModel {
    RED_GOLF("레드골프"),
    MS_GOLF("MS골프"),
    ;

    private final @NonNull String value;

    DeviceType(@NonNull String value) {
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
