package com.smart.booking.domain.reservation.dto;

import com.smart.booking.domain.reservation.enums.ReservationStatus;
import java.time.OffsetDateTime;

public class ReservationDateHistoryDto {
    private OffsetDateTime date;
    private ReservationStatus reservationStatus;
}
