package com.smart.booking.domain.external.dto;

import lombok.Builder;

@Builder
public record ExternalCancelPaymentResponseDto(

    Integer code,
    String message,

    PaymentAnnotationDto response
) {

}
