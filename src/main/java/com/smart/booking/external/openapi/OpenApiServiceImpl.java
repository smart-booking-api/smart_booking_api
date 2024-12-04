package com.smart.booking.external.openapi;

import com.mysema.commons.lang.Assert;
import com.smart.booking.external.openapi.model.GetRestDayInfoDto;
import com.smart.booking.external.openapi.model.GetRestDayInfoResponseDto;
import com.smart.booking.external.openapi.model.RestDayDto;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
class OpenApiServiceImpl implements OpenApiService {

    @Value("${external.openapi-rest-api-key}")
    private String openapiRestApiKey;

    @Override
    public @NonNull List<RestDayDto> getRestDayInfo(@NonNull GetRestDayInfoDto getRestDayInfoDto) {

        final RestTemplate restTemplate = new RestTemplate();

        final URI uri;

        try {
            uri = new URI(
                "https://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo?"
                    + "serviceKey=" + openapiRestApiKey
                    + "&solYear=" + getRestDayInfoDto.solYear()
                    + "&numOfRows=1000"
                    + "&pageNo=1"
                    + "&_type=json"
                    + getRestDayInfoDto.getSolMonth().map(solMonth -> "&solMonth=" + solMonth).orElse("")
            );
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        final var response = restTemplate.getForObject(
            uri,
            GetRestDayInfoResponseDto.class
        );

        Assert.isTrue(response != null, "response is null");

        final var item = response.response().body().items().item();

        if (item instanceof Map<?, ?> itemMap) {
            return List.of(RestDayDto.fromMap(itemMap));
        }

        if (item instanceof List<?> list) {
            return list.stream()
                .map(element -> (Map<?, ?>) element)
                .map(RestDayDto::fromMap)
                .toList();
        }

        throw new RuntimeException("GetRestDayInfoResponseDto's item is not Map or List");

    }
}
