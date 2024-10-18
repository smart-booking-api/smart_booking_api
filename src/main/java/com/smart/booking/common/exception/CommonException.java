package com.smart.booking.common.exception;

import com.smart.booking.common.enums.ResponseCode;
import lombok.NonNull;

public class CommonException extends Exception {

    private final ResponseCode responseCode;

    public CommonException(@NonNull ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String getMessage() {
        return responseCode.getMessage();
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public int getCode() {
        return responseCode.getCode();
    }


}
