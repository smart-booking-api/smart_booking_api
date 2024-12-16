package com.smart.booking.presentation.controller.endPoint;

public class PartnerEndPoint {

    private static final String BASE = "/api/partner/v1/partners";
    public static final String ME = BASE + "/me";
    public static final String CHANGE_PASSWORD = BASE + "/me/password";
    public static final String PARTNERS = BASE;
    public static final String DELETE_PARTNER = BASE + "/{partnerId}";
    public static final String PARTNER = BASE + "/{partnerId}";
    public static final String PARTNER_PASSWORD = BASE + "/password";

}
