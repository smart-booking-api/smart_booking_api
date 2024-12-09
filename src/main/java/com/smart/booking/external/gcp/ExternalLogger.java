package com.smart.booking.external.gcp;

import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.HttpRequest;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.Payload;
import com.google.cloud.logging.Payload.JsonPayload;
import com.google.cloud.logging.Severity;
import com.smart.booking.presentation.security.utils.RequestContext;
import java.util.Collections;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExternalLogger {

    private final Logging logging;

    public void errorLog(Exception e, String responseBody) {
        try {
            var pair = RequestContext.getCurrentHttpReqRes();
            var request = pair.getLeft();
            var response = pair.getRight();

            if (request != null && response != null) {
                send(
                    request.getMethod(),
                    request.getRequestURI(),
                    request.getHeader(HttpHeaders.USER_AGENT),
                    request.getHeader(HttpHeaders.AUTHORIZATION),
                    response.getStatus(),
                    null,
                    responseBody,
                    e
                );
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void send(
        String requestMethod,
        String url,
        String userAgent,
        String authorization,
        Integer status,
        String requestBody,
        String responseBody,
        Exception ex
    ) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String memberId = null;
            if (auth != null) {
                memberId = auth.getName();
            }

            String executionTime = RequestContext.getAttribute("executionTime");

            var logMap = new HashMap<String, String>();
            logMap.put("requestBody", requestBody);
            logMap.put("response", responseBody);
            logMap.put("memberId", memberId);
            logMap.put("executionTime", executionTime);
            if (ex != null) {
                logMap.put("exception", ex.getMessage());
            }
            if (ObjectUtils.isNotEmpty(authorization)) {
                logMap.put("authorization", authorization);
            }

            var builder = LogEntry.newBuilder(Payload.JsonPayload.of(logMap))
                .setSeverity(ex == null ? Severity.INFO : Severity.ERROR)
                .setLogName("smart-booking-api")
                .setResource(MonitoredResource.newBuilder("global").build());

            var logEntry = builder.setHttpRequest(
                HttpRequest.newBuilder()
                    .setRequestMethod(HttpRequest.RequestMethod.valueOf(requestMethod))
                    .setRequestUrl(url)
                    .setStatus(status)
                    .setUserAgent(userAgent)
                    .setRemoteIp(RequestContext.getClientIp())
                    .setServerIp(RequestContext.getServerIp())
                    .build()
            ).build();

            if (ex == null) {
                return;
            }

            logging.write(Collections.singleton(logEntry));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String sendCustomLogTest() {
        try {
            var logMap = new HashMap<String, String>();
            logMap.put("requestBody", "test");
            logMap.put("response", "response");
            logMap.put("phase", "phase");
            logMap.put("memberId", "memberId");
            logMap.put("executionTime", "123213");
            logMap.put("deviceModel", "deviceModel");
            logMap.put("osVersion", "osVersion");
            logMap.put("appVersion", "appVersion");

            LogEntry logEntry = LogEntry.newBuilder(JsonPayload.of(logMap))
                .setSeverity(Severity.INFO)
                .setLogName("smart-booking-api")
                .setResource(MonitoredResource.newBuilder("global").build())
                .build();

            logging.write(Collections.singleton(logEntry));
            return "Custom log sent to GCP Cloud Logging";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending log to GCP Cloud Logging";
        }
    }
}
