package com.smart.booking.common.enums;


import lombok.NonNull;

public enum ResponseCode implements EnumModel {
    COMMON_OK(0, "OK"),
    COMMON_UNKNOWN(101, "COMMON_UNKNOWN"),
    NOT_FOUND_ELEMENT(102, "유효한 데이터가 없습니다."),
    COMMON_BAD_REQUEST(102, "잘못된 요청 입니다."),
    NOT_FOUND_STORE(301, "매장이 존재하지 않습니다"),
    DUPLICATE_STORE_BUSINESS_REGISTRATION(302, "중복된 사업자 등록번호가 존재합니다."),
    NOT_FOUND_PARTNER(401, "파트너가 존재하지 않습니다"),
    ALREADY_INITIALIZED_PARTNER(402, "이미 초기화된 파트너입니다."),
    NOT_INITIALIZED_PARTNER(403, "초기화 되지 않은 파트너입니다."),
    DUPLICATE_PARTNER_BUSINESS_REGISTRATION(404, "중복된 사업자 등록번호가 존재합니다."),
    NOT_PERMITTED_PARTNER_TYPE(405, "권한이 없는 파트너 입니다."),
    NOT_MATCHED_PARTNER_PASSWORD(406, "비밀번호가 일치하지 않습니다."),
    NOT_FOUND_USER(501, "유저가 존재하지 않습니다"),
    NOT_FOUND_THIRD_PARTY_ACCOUNT(502, "존재하지 않는 소셜 계정입니다."),
    NOT_MATCHED_PASSWORD(503, "패스워드가 일치하지 않습니다."),
    NOT_VALID_REFRESH_TOKEN(504, "유효하지 않은 리프레시 토큰입니다"),

    /**
     * 600~700
     */
    NOT_REFUNDABLE_STATUS_ERROR(601, "환불할 수 없는 상태입니다."),
    ALREADY_SAVED_PAYMENT_ERROR(602, "이미 결제된 정보가 있습니다."),
    NO_DATA_SAVED_PAYMENT_TRACKING_INFO(603, "결제 요청한 데이터가 없습니다."),
    NOT_FOUND_TEE_BOX(701, "타석이 존재하지 않습니다."),
    DUPLICATE_TEE_BOX_SHARE_PARTNER_TYPE(702, "중복된 파트너 타입이 존재합니다."),
    EXCEED_TEE_BOX_SHARE_SUM(703, "타석 지분 비율이 100을 초과합니다."),

    /**
     * 800
     * 예약, 선점
     */
    ALREADY_LOCK_RESERVATION(801, "다른 이용자가 예약중입니다."),
    NOT_FOUND_RESERVATION_TIME(802, "예약시간이 존재하지 않습니다."),
    NOT_MY_RESERVATION_LOCK(803, "다른 이용자의 선점락이 존재합니다"),
    NOT_FOUND_RESERVATION(804, "존재하지 않는 예약입니다."),

    /**
     * 900
     * 장비
     */
    NOT_FOUND_DEVICE(901, "장비가 존재하지 않습니다."),

    /**
     * 1000~
     * 인증
     */
    NOT_FOUND_PHONE_AUTH_CODE(1001, "인증코드가 존재하지 않습니다."),
    NOT_MATCHED_PHONE_AUTH_CODE(1002, "인증코드가 일치하지 않습니다."),
  
    /**
     * 1400
     * common
     */
    FIREBASE_ERROR(1401, "Firebase 를 호출하는 도중 오류가 발생했습니다.")
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
