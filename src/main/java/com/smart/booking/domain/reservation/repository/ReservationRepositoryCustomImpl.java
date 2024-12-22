package com.smart.booking.domain.reservation.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smart.booking.domain.reservation.entity.QReservation;
import com.smart.booking.domain.reservation.entity.QReservationMember;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.enums.ReservationStatus;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryCustomImpl implements ReservationRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QReservation qReservation = QReservation.reservation;
    private final QReservationMember qReservationMember = QReservationMember.reservationMember;

    @Override
    public List<Reservation> getByMemberIdAndYearMonth(String memberId, String yearMonth) {
      return jpaQueryFactory.selectFrom(qReservation)
            .where(qReservationMember.member.id.eq(memberId).and(matchReservationDate(qReservation.reservationDate, yearMonth)))
          .groupBy(qReservation.reservationDate)
          .having(qReservation.reservationStatus.eq(ReservationStatus.RESERVED)
                  .or(qReservation.reservationStatus.eq(ReservationStatus.CANCELED).and(qReservation.reservationStatus.ne(ReservationStatus.RESERVED)))
                  .or(qReservation.reservationStatus.eq(ReservationStatus.COMPLETED).and(qReservation.reservationStatus.ne(ReservationStatus.RESERVED)).and(qReservation.reservationStatus.ne(ReservationStatus.CANCELED)))
          ).fetch();
    }

    private BooleanExpression matchReservationDate(DatePath<LocalDate> reservationDate, String yearMonth) {
        StringTemplate stringTemplate = Expressions.stringTemplate("FORMATDATETIME({0}, 'yyyyMM')", reservationDate);

        return stringTemplate.eq(yearMonth);
    }
}
