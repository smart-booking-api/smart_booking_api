package com.smart.booking.domain.reservation.repository;

import com.smart.booking.domain.reservation.entity.ReservationLock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationLockRepository extends JpaRepository<ReservationLock, String> {

}
