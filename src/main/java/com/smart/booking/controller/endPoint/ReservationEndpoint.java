package com.smart.booking.controller.endPoint;

public class ReservationEndpoint {
    public static final String RESERVATION_ROOT = "/reservation";
    public static final String RESERVATION_CHECK_LOCK = RESERVATION_ROOT + "/isLocked";
    public static final String RESERVATION_CREATE_LOCK = RESERVATION_ROOT + "/lock";
    public static final String RESERVATION_DELETE_LOCK = RESERVATION_ROOT + "/lock";
}
