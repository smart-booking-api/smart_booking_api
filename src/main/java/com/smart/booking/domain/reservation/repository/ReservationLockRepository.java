package com.smart.booking.domain.reservation.repository;

import com.smart.booking.domain.reservation.entity.ReservationLock;
import org.springframework.data.repository.CrudRepository;

public interface ReservationLockRepository extends CrudRepository<ReservationLock, String> {
}
