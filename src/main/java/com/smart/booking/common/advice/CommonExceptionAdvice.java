package com.smart.booking.common.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.external.gcp.ExternalLogger;
import java.io.PrintWriter;
import java.io.StringWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.cfgxml.internal.CfgXmlAccessServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CommonExceptionAdvice {

    private final ExternalLogger logger;
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @ExceptionHandler(CommonException.class)
    public ResponseEntity<?> commonExceptionHandler(CommonException e) {
        var commonResponse = getCommonResponse(
            e.getResponseCode(),
            e
        );

        sendExternalLog(e, commonResponse);

        if (e.getHttpStatus().is2xxSuccessful()) {
            return ResponseEntity.ok(commonResponse);
        }
        return ResponseEntity.status(e.getHttpStatus())
            .body(commonResponse);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        var commonResponse = getCommonResponse(
            ResponseCode.COMMON_BAD_REQUEST,
            e
        );

        sendExternalLog(e, commonResponse);

        return ResponseEntity.ok(commonResponse);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<?> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        var commonResponse = getCommonResponse(
            ResponseCode.COMMON_BAD_REQUEST,
            e
        );

        sendExternalLog(e, commonResponse);

        return ResponseEntity.ok(commonResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        var commonResponse = getCommonResponse(
            ResponseCode.COMMON_BAD_REQUEST,
            e
        );

        sendExternalLog(e, commonResponse);

        return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value())
            .body(commonResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> errorHandler(Exception e) {
        var commonResponse = getCommonResponse(
            ResponseCode.COMMON_UNKNOWN,
            e
        );

        sendExternalLog(e, commonResponse);

        return ResponseEntity.ok(commonResponse);
    }

    private CommonResponse<Object> getCommonResponse(
        ResponseCode responseCode,
        Throwable e
    ) {
        log.error("# [{}}] exception occurred. stackTrace : {}", e.getClass().getName(), e.getStackTrace());

        String responseMessage;

        if (e instanceof CommonException) {
            responseMessage = e.getMessage();
        } else {
            responseMessage = shortenedStackTrace(e, 1);
        }

        return CommonResponse.builder()
            .responseCode(responseCode.getCode())
            .message(responseMessage)
            .build();
    }

    private String shortenedStackTrace(Throwable e, int maxLines) {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        String[] lines = writer.toString().split("\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(lines.length, maxLines); i++) {
            sb.append(lines[i]).append("\n");
        }
        return sb.toString();
    }

    private <T extends Exception> void sendExternalLog(T e, CommonResponse<Object> commonResponse) {

        try {
            log.info("send error log");
            logger.errorLog(e, objectMapper.writeValueAsString(commonResponse));
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

}
