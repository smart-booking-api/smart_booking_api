package com.smart.booking.presentation.controller.endPoint;

public class AuthEndPoint {

    public static final String BASE = "/api";

    public static final String V1 = BASE + "/v1/auth";

    public static final String REFRESH_TOKEN = V1 + "/refresh";

    public static final String THIRD_PARTY_LOGIN = V1 + "/third-party";

    public static final String LOGIN = V1 + "/login";

    public static final String PHONE_AUTH = V1 + "/phone-auth";

    public static final String PHONE_AUTH_VERIFY = V1 + "/phone-auth/verify";

    public static final String SIGN_UP = V1 + "/sign-up";

}
