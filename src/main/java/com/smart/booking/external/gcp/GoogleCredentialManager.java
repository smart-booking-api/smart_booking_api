package com.smart.booking.external.gcp;

import com.google.auth.oauth2.GoogleCredentials;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GoogleCredentialManager {

    private GoogleCredentials credentials;

    @PostConstruct
    protected void init() throws IOException {
        credentials = GoogleCredentials.fromStream(new ClassPathResource("smart-booking-google.json").getInputStream());
    }


    public String getAccessToken() throws IOException {
        credentials.refreshIfExpired();
        return credentials.getAccessToken().getTokenValue();
    }
}
