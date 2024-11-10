package com.smart.booking.domain.reservation.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.entity.ReservationMember;
import com.smart.booking.domain.reservation.enums.ReservationStatus;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ReservationRepositoryTest {
    @Autowired
    private ReservationRepository reservationRepository;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        Member member = new Member("1", MemberType.USER);
        ReservationMember reservationMember = new ReservationMember(member, "테스트", "01036010559");
        // 테스트 전에 데이터 초기화
        reservation = new Reservation("1", null, 12345678, null, LocalDate.now(), "01","02", ReservationStatus.RESERVED, reservationMember, "111");
        reservationRepository.save(reservation); // 데이터베이스에 예약 저장
    }

    @Test
    void testFindByReservationNumber() {
        // 예약 번호로 검색
        Optional<Reservation> found = reservationRepository.findByReservationNo(12345678);

        // 결과 검증
        assertTrue(found.isPresent());
    }
}