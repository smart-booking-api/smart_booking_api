package com.smart.booking.data.store.data_source;

import static org.springframework.http.HttpMethod.GET;

import com.smart.booking.data.store.model.ParseRegionResultDto;
import java.util.Objects;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
class RegionDataSourceImpl implements RegionDataSource {

    @Value("${kakao-rest-api-key}")
    private String kakaoRestApiKey;

    @Override
    public @NonNull ParseRegionResultDto parseRegion(@NonNull String address) {
        final String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + address;
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoRestApiKey);

        final HttpEntity<String> entity = new HttpEntity<>(headers);

        final ResponseEntity<ParseRegionResultDto> response = restTemplate.exchange(
            url,
            GET,
            entity,
            ParseRegionResultDto.class
        );

        return Objects.requireNonNull(response.getBody());
    }

}
