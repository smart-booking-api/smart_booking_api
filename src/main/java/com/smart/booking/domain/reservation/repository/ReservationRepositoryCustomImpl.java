package com.smart.booking.domain.reservation.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smart.booking.domain.reservation.entity.QReservation;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.tee_box.entity.QTeeBox;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryCustomImpl implements ReservationRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    private final QReservation qReservation = QReservation.reservation;
    private final QTeeBox qTeeBox = QTeeBox.teeBox;

    @Override
    public List<Reservation> getReservationByTeeBoxIdAndReservationDate(String teeBoxId, LocalDate reservationDate) {
        return jpaQueryFactory.selectFrom(qReservation)
            .innerJoin(qReservation.teeBox, qTeeBox)
            .where(qTeeBox.id.eq(teeBoxId).and(qReservation.reservationDate.eq(reservationDate)))
            .fetch();
    }
}
