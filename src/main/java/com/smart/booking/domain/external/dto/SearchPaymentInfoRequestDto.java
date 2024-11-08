package com.smart.booking.domain.external.dto;

import jakarta.annotation.Nullable;
import lombok.NonNull;

public record SearchPaymentInfoRequestDto(
    //포트원 거래고유번호
    @NonNull String merchantUid,

    @Nullable String sorting

) {

}
