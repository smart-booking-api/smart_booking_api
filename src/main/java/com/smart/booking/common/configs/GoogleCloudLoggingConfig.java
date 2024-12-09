package com.smart.booking.common.configs;

import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;
import com.smart.booking.external.gcp.GoogleCredentialManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GoogleCloudLoggingConfig {

    private final GoogleCredentialManager googleCredentialManager;

    @Value("${spring.cloud.gcp.project-id}")
    String projectId;

    @Bean
    public Logging googleCloudLogging() {
        var builder = LoggingOptions.newBuilder();
        builder.setProjectId(projectId);
        builder.setCredentials(googleCredentialManager.getCredentials());
        return builder.build().getService();
    }
}