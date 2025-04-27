package com.smart.booking.presentation.controller.endPoint;

public class PosReservationEndpoint {

    private static final String BASE = "/api/pos";
    private static final String V1 = BASE + "/v1/reservation";
    public static final String GET_RESERVATION = V1 + "/{reservationNo}";
    public static final String START_GAME = V1 + "/game/start/{reservationNo}";
    public static final String GET_ENABLE_RESERVATION_TIME = BASE + "/time";
    public static final String RESERVATION_LOCK = BASE + "/lock";
}
