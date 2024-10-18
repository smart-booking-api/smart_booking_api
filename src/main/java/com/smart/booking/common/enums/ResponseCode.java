package com.smart.booking.common.enums;


import lombok.NonNull;

public enum ResponseCode implements EnumModel {
    COMMON_OK(0, "OK"),
    ;

    private final int code;
    private final String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public @NonNull String getKey() {
        return Integer.toString(code);
    }

    @Override
    public @NonNull String getValue() {
        return getMessage();
    }

    public int getCode() {
        return code;
    }

    public @NonNull String getMessage() {
        return message;
    }
}
