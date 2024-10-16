package com.smart.booking.domain.partner.enums;

import com.smart.booking.common.enums.EnumModel;
import lombok.NonNull;

public enum PartnerType implements EnumModel {
    // 관리자 ( 시뮬랩 ) 계정
    M("관리자"),
    // 지사 ( 설치 대상 지역 )
    B("지사"),
    // 투자사 ( 장비, 매장 운영시 투자 하는 사람 )
    V("투자사"),
    // 서비스제공사 ( 골프 프로그램 제공 회사 )
    P("서비스 제공사"),
    /// 운영사 ( 매장 운영사 )
    O("운영사"),
    ;

    private final @NonNull String value;

    PartnerType(@NonNull String value) {

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
