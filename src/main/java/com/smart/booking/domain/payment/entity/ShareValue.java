package com.smart.booking.domain.payment.entity;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class ShareValue {

    // PERCENTAGE/FIXED_AMOUNT
    private CalcType type;
    // 정률일 경우 비율(%), 정액일 경우 금액(원)
    private BigDecimal value;

    public ShareValue(CalcType type, BigDecimal value) {
        this.type = type;
        this.value = value;
    }

    public ShareValue() {
    }

    public CalcType getType() {
        return type;
    }

    public BigDecimal getValue() {
        return value;
    }

    // 파트너의 결제 지분을 계산하는 로직
    public BigDecimal calculateShare(BigDecimal totalAmount) {
        if (type == CalcType.PERCENTAGE) {
            // 정률 계산
            return totalAmount.multiply(value).divide(BigDecimal.valueOf(100));
        } else if (type == CalcType.FIXED_AMOUNT) {
            // 고정 금액일 경우 바로 리턴
            return value;
        }
        return BigDecimal.ZERO;
    }
}