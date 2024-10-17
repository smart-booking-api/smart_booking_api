package com.smart.booking.domain.device.enums;

import com.smart.booking.common.enums.EnumModel;
import lombok.NonNull;

public enum DeviceStatus implements EnumModel {
    // 타석은 설정되었으나 서버와 연결되지 않음(사용불가상태)
    NOC("사용불가"),
    // 타석 동작중
    RUN("대기"),
    // 타석 사용 가능 (서버와 연결되어 제어가능상태)
    WAIT("게임중"),
    // 장비고장으로 타석 사용불가
    ERR("고장"),
    ;

    private final String value;

    DeviceStatus(String value) {
        this.value = value;
    }


    @Override
    public @NonNull String getKey() {
        return null;
    }

    @Override
    public @NonNull String getValue() {
        return null;
    }
}
