package com.smart.booking.presentation.controller.endPoint;

public class ReservationEndpoint {

    public static final String RESERVATION_ROOT = "/api/user/v1/reservation";
    public static final String RESERVATION_LOCK = RESERVATION_ROOT + "/lock";
    public static final String GET_ENABLE_RESERVATION_TIME = RESERVATION_ROOT + "/time";
    public static final String GET_MY_RESERVATION = RESERVATION_ROOT + "/me/{startDate}";
    public static final String CREATE_USER_RESERVATION = RESERVATION_ROOT;
    public static final String MONTHLY_RESERVATION_HISTORY = RESERVATION_ROOT + "/{year}/{month}";
    public static final String UPDATE_RESERVATION_STATUS = RESERVATION_ROOT + "/status";

}
