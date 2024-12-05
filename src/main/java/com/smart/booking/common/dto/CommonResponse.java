package com.smart.booking.common.dto;

import com.smart.booking.common.enums.ResponseCode;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonResponse<T> {

    @NotNull
    private Integer responseCode;
    @NotNull
    private String message;
    private T result;

    @Builder
    public CommonResponse(Integer responseCode, String message, T result) {
        this.responseCode = responseCode;
        this.message = message;
        this.result = result;
    }

    public CommonResponse(T result) {
        this.responseCode = ResponseCode.COMMON_OK.getCode();
        this.message = ResponseCode.COMMON_OK.getMessage();
        this.result = result;
    }
}
