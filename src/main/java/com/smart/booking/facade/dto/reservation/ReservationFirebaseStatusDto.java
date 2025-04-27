package com.smart.booking.facade.dto.reservation;

import com.smart.booking.domain.payment.entity.PaymentStatus;
import lombok.Getter;

@Getter
public class ReservationFirebaseStatusDto {
    private String trackingId;
    private String memberId;
    private PaymentStatus status;
    private int reservationNo;


    public ReservationFirebaseStatusDto(String trackingId, String memberId, PaymentStatus status, int reservationNo) {
        this.trackingId = trackingId;
        this.memberId = memberId;
        this.status = status;
        this.reservationNo = reservationNo;
    }
}
