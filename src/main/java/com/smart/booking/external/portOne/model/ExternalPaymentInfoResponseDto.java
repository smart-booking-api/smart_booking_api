package com.smart.booking.external.portOne.model;

import lombok.Builder;

@Builder
public record ExternalPaymentInfoResponseDto(

    Integer code,
    String message,

    PaymentAnnotationDto response
) {

}
