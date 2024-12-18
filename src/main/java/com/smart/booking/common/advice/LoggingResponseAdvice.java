package com.smart.booking.common.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.common.wrapper.LoggingRequestWrapper;
import com.smart.booking.external.gcp.ExternalLogger;
import com.smart.booking.presentation.security.utils.RequestContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class LoggingResponseAdvice implements ResponseBodyAdvice<Object> {

    private ExternalLogger logger;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.getParameterType().equals(ResponseEntity.class) &&
            !returnType.getParameterType().equals(CommonResponse.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter parameter, MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType,
        ServerHttpRequest request, ServerHttpResponse response) {
        try {
            var pair = RequestContext.getCurrentHttpReqRes();
            var httpRequest = pair.getLeft();
            var httpResponse = pair.getRight();
            String requestBody = null;
            if (httpRequest instanceof LoggingRequestWrapper) {
                requestBody = ((LoggingRequestWrapper) httpRequest).getBody();
            }
            logger.send(
                httpRequest.getMethod(),
                httpRequest.getRequestURI(),
                httpRequest.getHeader(HttpHeaders.USER_AGENT),
                httpRequest.getHeader(HttpHeaders.AUTHORIZATION),
                httpResponse.getStatus(),
                requestBody,
                ObjectUtils.isEmpty(body) ? null : objectMapper.writeValueAsString(body),
                null
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }
}
