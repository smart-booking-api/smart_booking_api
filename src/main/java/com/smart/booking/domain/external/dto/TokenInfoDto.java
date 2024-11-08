package com.smart.booking.domain.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenInfoDto(

    @JsonProperty("access_token")
    String accessToken,
    @JsonProperty("now")
    String now,
    @JsonProperty("expired_at")
    String expiredAt
) {

}
