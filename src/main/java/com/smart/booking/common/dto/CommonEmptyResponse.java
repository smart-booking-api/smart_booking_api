package com.smart.booking.common.dto;

import com.smart.booking.common.enums.ResponseCode;
import lombok.Getter;


@Getter
public abstract class CommonEmptyResponse {

    private final Integer responseCode;
    private final String message;
    private final Void result;

    public CommonEmptyResponse() {
        this.responseCode = ResponseCode.COMMON_OK.getCode();
        this.message = ResponseCode.COMMON_OK.getMessage();
        this.result = null;
    }
}
