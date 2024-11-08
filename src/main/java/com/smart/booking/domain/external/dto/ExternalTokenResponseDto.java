package com.smart.booking.domain.external.dto;

import lombok.Builder;

@Builder

public record ExternalTokenResponseDto(

    Integer code,
    String message,

    TokenInfoDto response
) {

}
