package com.smart.booking.presentation.security.config;

import com.smart.booking.common.util.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
        throws IOException, ServletException {
        sendErrorMessage(response, "권한이 없습니다");
    }

    private void sendErrorMessage(HttpServletResponse response, String message) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JsonUtil.convertToString(
                ErrorResponse.builder()
                    .status(HttpStatus.FORBIDDEN)
                    .message(message)
                    .build()
            )
        );
    }
}
