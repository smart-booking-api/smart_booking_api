package com.smart.booking.common.enums;


import lombok.NonNull;

public enum ResponseCode implements EnumModel {
    COMMON_OK(0, "OK"),
    COMMON_UNKNOWN(101, "COMMON_UNKNOWN"),
    NOT_FOUND_ELEMENT(102, "유효한 데이터가 없습니다."),
    COMMON_BAD_REQUEST(102, "잘못된 요청 입니다."),

    NOT_FOUND_STORE(300, "매장이 존재하지 않습니다"),
    NOT_FOUND_PARTNER(400, "파트너가 존재하지 않습니다"),
    ALREADY_INITIALIZED_PARTNER(401, "이미 초기화된 파트너입니다."),
    NOT_INITIALIZED_PARTNER(402, "초기화 되지 않은 파트너입니다."),
    NOT_FOUND_USER(500, "유저가 존재하지 않습니다"),
    NOT_FOUND_THIRD_PARTY_ACCOUNT(501, "존재하지 않는 소셜 계정입니다."),
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
