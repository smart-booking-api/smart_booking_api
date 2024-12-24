package com.smart.booking.facade.dto.reservation;

import com.smart.booking.domain.payment.entity.PaymentStatus;
import lombok.Getter;

@Getter
public class ReservationFirebaseStatusDto {
    private String trackingId;
    private String memberId;
    private PaymentStatus status;


    public ReservationFirebaseStatusDto(String trackingId, String memberId, PaymentStatus status) {
        this.trackingId = trackingId;
        this.memberId = memberId;
        this.status = status;
    }
}
