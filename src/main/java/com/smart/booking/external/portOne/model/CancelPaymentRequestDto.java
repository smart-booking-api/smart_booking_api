package com.smart.booking.external.portOne.model;

import jakarta.annotation.Nullable;
import lombok.NonNull;

public record CancelPaymentRequestDto(
    //포트원 거래고유번호
    @NonNull String imp_uid,
    //고객사 주문번호
    @NonNull String merchant_uid,
    //(부분)취소 요청 금액
    @Nullable Integer amount,
    String reason
) {

}
