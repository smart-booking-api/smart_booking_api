package com.smart.booking.presentation.controller.endPoint;

public class PartnerEndPoint {

    private static final String BASE = "/api/partner";
    private static final String V1 = BASE + "/v1/partners";
    public static final String CREATE_PARTNER = V1;
    public static final String ME = V1 + "/me";
    public static final String INIT_PARTNER = ME + "/init";
    public static final String CHANGE_MY_PASSWORD = ME + "/password";
    public static final String PARTNERS = V1;
    public static final String DELETE_PARTNER = V1 + "/{partnerId}";
    public static final String PARTNER = V1 + "/{partnerId}";
    public static final String UPDATE_PARTNER = V1 + "/{partnerId}";
}
