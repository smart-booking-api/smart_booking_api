package com.smart.booking.facade.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

public record CompletePaymentRequestDto(
    @JsonProperty("imp_uid")
    @NonNull String impUid,
    @JsonProperty("merchant_uid")
    @NonNull String merchantUid
) {

}