package com.smart.booking.domain.reservation.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ReservationLock {
    @Id
    @TsidGenerator
    private String id;
    private String storeId;
    private String timeId;
    private String email;

    public static ReservationLock create(String storeId, String timeId, String email) {
        ReservationLock reservationLock = new ReservationLock();
        reservationLock.setStoreId(storeId);
        reservationLock.setTimeId(timeId);
        reservationLock.setEmail(email);
        return reservationLock;
    }
}
