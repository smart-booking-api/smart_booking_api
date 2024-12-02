package com.smart.booking.presentation.controller.endPoint;

public class PartnerEndPoint {

    public static final String PARTNER = "/api/partner/v1/partners";

    public static final String ME = PARTNER + "/me";

    public static final String CHANGE_PASSWORD = ME + "/password";

    public static final String PARTNERS = PARTNER + "?";
}
