package com.smart.booking.domain.common.enums;

import com.smart.booking.common.enums.EnumModel;
import lombok.NonNull;

public enum TeeBoxType implements EnumModel {
    ROOM("룸형"),
    KIOSK("키오스크형"),
    IN_HOUSE("구매형"),
    ;


    private final String value;

    TeeBoxType(String value) {
        this.value = value;
    }

    @Override
    public @NonNull String getKey() {
        return name();
    }

    @Override
    public @NonNull String getValue() {
        return this.value;
    }
}
