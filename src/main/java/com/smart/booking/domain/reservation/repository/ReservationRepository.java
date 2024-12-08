package com.smart.booking.domain.reservation.repository;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, String>, ReservationRepositoryCustom {
    List<Reservation> findAllByReservationMemberMemberAndReservationDateIsAfter(Member member, LocalDate reservationDate);
    List<Reservation> findAllByStoreAndReservationDate(Store store, LocalDate reservationDate);
    Optional<Reservation> findByReservationNo(int number);

    List<Reservation> getReservationByTeeBoxAndReservationDate(TeeBox teeBox, LocalDate reservationDate);
}
