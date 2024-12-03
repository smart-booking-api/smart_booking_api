package com.smart.booking.facade.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class AdminLogin {
    @Getter
    @Schema(description = "로그인 요청 DTO")
    @NoArgsConstructor
    public static class loginRequest {
        @NotBlank(message = "로그인 ID를 입력해주세요.")
        @Schema(description = "로그인 ID")
        private String loginId;

        @NotBlank(message = "로그인 패스워드를 입력해주세요.")
        @Schema(description = "로그인 패스워드")
        private String password;

        public loginRequest(String loginId, String password) {
            this.loginId = loginId;
            this.password = password;
        }
    }

    @Getter
    @Schema(description = "로그인 응답 DTO")
    public static class loginResponse {
        @Schema(description = "access 토큰")
        private String accessToken;
        @Schema(description = "refresh 토큰")
        private String refreshToken;

        public loginResponse(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }
}
