package com.smart.booking.facade.dto.auth;

import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ThirdPartyLogin {
    @Getter
    @Schema(description = "서드파티 로그인 요청 DTO")
    @NoArgsConstructor
    public static class thirdLoginRequest {
        @NotBlank(message = "로그인 ID를 입력해주세요.")
        @Schema(description = "로그인 ID")
        private String providerUserId;

        @NotBlank(message = "제공자를 입력해주세요")
        @Schema(description = "provider")
        private ThirdPartyAccountProvider provider;

        public thirdLoginRequest(String providerUserId, ThirdPartyAccountProvider provider) {
            this.providerUserId = providerUserId;
            this.provider = provider;
        }
    }

    @Getter
    @Schema(description = "로그인 응답 DTO")
    public static class thirdLoginResponse {
        @Schema(description = "access 토큰")
        private String accessToken;
        @Schema(description = "refresh 토큰")
        private String refreshToken;

        public thirdLoginResponse(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }
}
