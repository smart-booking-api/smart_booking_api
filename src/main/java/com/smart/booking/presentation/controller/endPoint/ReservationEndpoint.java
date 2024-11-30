package com.smart.booking.presentation.controller.endPoint;

public class ReservationEndpoint {
    public static final String RESERVATION_ROOT = "/reservation";
    public static final String RESERVATION_CREATE_PHONE_RESERVATION = RESERVATION_ROOT + "/admin/reservation";
    public static final String RESERVATION_LOCK = RESERVATION_ROOT + "/lock";
    public static final String GET_ENABLE_RESERVATION_TIME = RESERVATION_ROOT + "/reservation/time";
    public static final String GET_MY_RESERVATION = RESERVATION_ROOT + "/user/my/{startDate}";
    public static final String CREATE_USER_RESERVATION = RESERVATION_ROOT + "/user/reservation";
    public static final String MONTHLY_USER_RESERVATION_HISTORY = RESERVATION_ROOT + "/user/reservation/history";
    public static final String UPDATE_RESERVATION_STATUS = RESERVATION_ROOT + "/user/reservation/status";

}
