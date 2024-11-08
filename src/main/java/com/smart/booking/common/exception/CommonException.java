package com.smart.booking.common.exception;

import com.smart.booking.common.enums.ResponseCode;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

public class CommonException extends RuntimeException {

    protected HttpStatus httpStatus;
    private final ResponseCode responseCode;

    protected String message;

    public CommonException(@NonNull ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public CommonException(@NonNull ResponseCode responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public CommonException(HttpStatus httpStatus, @NonNull ResponseCode responseCode) {
        this.httpStatus = httpStatus;
        this.responseCode = responseCode;
    }

    @Override
    public String getMessage() {
        return responseCode.getMessage();
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getCode() {
        return responseCode.getCode();
    }


}
