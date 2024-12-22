package com.smart.booking.presentation.controller.endPoint;

public class PartnerReservationEndpoint {

    public static final String RESERVATION_ROOT = "/api/partner/v1/reservation";
    public static final String GET_ENABLE_RESERVATION_TIME = RESERVATION_ROOT + "/time";
    public static final String PHONE_RESERVATION = RESERVATION_ROOT + "/phone";
    public static final String RESERVATION_PARTNER_CANCEL_RESERVATION = RESERVATION_ROOT + "/{reservationId}";

}
