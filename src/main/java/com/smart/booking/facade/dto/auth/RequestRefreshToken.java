package com.smart.booking.facade.dto.auth;

import lombok.Getter;

@Getter
public class RequestRefreshToken {
    @Getter
    public static class requestToken {
        private String refreshToken;

        public requestToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }

    @Getter
    public static class responseToken {
        private String accessToken;

        public responseToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
