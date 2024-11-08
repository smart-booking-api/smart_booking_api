package com.smart.booking.domain.external.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.booking.common.client.ExternalWebClient;
import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.external.dto.ExternalPaymentInfoResponseDto;
import com.smart.booking.domain.external.dto.ExternalTokenResponseDto;
import com.smart.booking.domain.external.dto.SearchPaymentInfoRequestDto;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import java.util.Collections;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class PortOneClient {

    public static final String PORTONE_REST_API_KEY = "7208642805121630";
    public static final String PORTONE_REST_API_SECRET = "JaqBRphzG4TomXaSW3D6wpCFFoqhI0XhHdB2yXzEt0aeeOgOzbSvnh5d9OkYgFkb8WMlCFGllHkDzdFd";

    private final ExternalWebClient webClient;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();


    /**
     * 토큰 발급
     */
    public ExternalTokenResponseDto getToken() {
        var responseOptional = webClient.post(
            ExternalTokenResponseDto.class,
            "api.iamport.kr/users/getToken",
            headers,
            new LinkedMultiValueMap<>(),
            Map.of("imp_key", PORTONE_REST_API_KEY, "imp_secret", PORTONE_REST_API_SECRET),
            (Object) null
        ).orElse(ExternalTokenResponseDto.builder().build());

        if (responseOptional.message() != null) {
            throw new CommonException(ResponseCode.COMMON_BAD_REQUEST, responseOptional.message());
        }

        log.info("# [PortOntClient] getToken response : {}", responseOptional);
        return responseOptional;

    }

    /**
     * 결제 내역 단건 조회
     */
    public ExternalPaymentInfoResponseDto searchPayment(String token, SearchPaymentInfoRequestDto request) {

        var responseOptional = webClient.get(
            ExternalPaymentInfoResponseDto.class,
            String.join(
                "/",
                "api.iamport.kr/payments/find",
                request.merchantUid(),
                PaymentStatus.COMPLETE.getExternalValue()
            ),
            Map.of("Authorization", token),
            requestToParams(request)
        ).orElse(ExternalPaymentInfoResponseDto.builder().build());

        if (responseOptional.message() != null) {
            throw new CommonException(ResponseCode.COMMON_BAD_REQUEST, responseOptional.message());
        }

        log.info("# [PortOntClient] searches response : {}", responseOptional);
        return responseOptional;
    }

    private static LinkedMultiValueMap<String, String> requestToParams(Object request) {
        var queryParams = new LinkedMultiValueMap<String, String>();
        Map<String, String> maps = objectMapper.convertValue(request, new TypeReference<>() {
        });
        maps.values().removeAll(Collections.singleton(null));
        queryParams.setAll(maps);
        return queryParams;
    }
}
