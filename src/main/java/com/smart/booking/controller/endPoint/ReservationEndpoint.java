package com.smart.booking.controller.endPoint;

public class ReservationEndpoint {
    public static final String RESERVATION_ROOT = "/reservation";
    public static final String RESERVATION_CHECK_LOCK = RESERVATION_ROOT + "/isLocked";
    public static final String RESERVATION_CREATE_LOCK = RESERVATION_ROOT + "/lock";
    public static final String RESERVATION_DELETE_LOCK = RESERVATION_ROOT + "/lock";
    public static final String GET_ENABLE_RESERVATION_TEE_BOX = RESERVATION_ROOT + "/enable/teeBox";
    public static final String GET_MY_RESERVATION = RESERVATION_ROOT + "/user/my";
    public static final String CREATE_USER_RESERVATION = RESERVATION_ROOT + "/user/reservation";
    public static final String MONTHLY_USER_RESERVATION_HISTORY = RESERVATION_ROOT + "/user/reservation/history";
    public static final String UPDATE_RESERVATION_STATUS = RESERVATION_ROOT + "/user/reservation/status";

}
