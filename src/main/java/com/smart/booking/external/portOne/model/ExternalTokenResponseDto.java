package com.smart.booking.external.portOne.model;

import lombok.Builder;

@Builder

public record ExternalTokenResponseDto(

    Integer code,
    String message,

    TokenInfoDto response
) {

}
