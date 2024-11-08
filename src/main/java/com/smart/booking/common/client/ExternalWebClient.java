package com.smart.booking.common.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExternalWebClient {

    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private <T> T getResponse(Class<T> clazz, Optional<String> responseBody) {
        if (responseBody.isEmpty()) {
            log.info("# [ExternalWebClient] {} getResponse is empty", clazz.getName());
            return null;
        }

        if (clazz == String.class) {
            return (T) responseBody.orElse(null);
        }

        try {
            return objectMapper.readValue(responseBody.get(), clazz);
        } catch (JsonProcessingException e) {
            log.error("# [ExternalWebClient] {} getResponse error = {}", clazz.getName(), e.getMessage());
            return null;
        }
    }

    public <T> Optional<T> get(
        Class<T> clazz,
        String uri,
        Map<String, String> header,
        LinkedMultiValueMap<String, String> params
    ) {
        Optional<String> responseBody = webClient.get()
            .uri(uriBuilder -> uriBuilder.path(uri).queryParams(params).build())
            .header("Authorization", header.get("Authorization"))
            .retrieve()
            .onStatus(status -> status.is4xxClientError() || status.is5xxServerError()
                , clientResponse -> clientResponse.bodyToMono(String.class)
                    .map(message -> new CommonException(ResponseCode.NOT_FOUND_ELEMENT))
            )
            .bodyToMono(String.class)
            .blockOptional();

        return Optional.ofNullable(getResponse(clazz, responseBody));
    }

    public <T> Optional<T> post(
        Class<T> clazz,
        String uri,
        LinkedMultiValueMap<String, String> headers,
        LinkedMultiValueMap<String, String> params,
        Object requestBody,
        Object... uriVariables
    ) {
        Optional<String> responseBody = webClient.post()
            .uri(uriBuilder -> uriBuilder.path(uri)
                .queryParams(params)
                .build(uriVariables))
            .headers(httpHeaders -> httpHeaders.addAll(headers))
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(requestBody))
            .retrieve()
            .onStatus(status -> status.is4xxClientError() || status.is5xxServerError()
                , clientResponse -> clientResponse.bodyToMono(String.class)
                    .map(message -> new CommonException(ResponseCode.COMMON_UNKNOWN))
            )
            .bodyToMono(String.class)
            .blockOptional();

        return Optional.ofNullable(getResponse(clazz, responseBody));
    }

}
