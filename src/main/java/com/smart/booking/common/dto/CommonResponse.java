package com.smart.booking.common.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonResponse<T> {

    private Integer responseCode;
    private String message;
    private T result;

    @Builder
    public CommonResponse(Integer responseCode, String message, T result) {
        this.responseCode = responseCode;
        this.message = message;
        this.result = result;
    }
}
