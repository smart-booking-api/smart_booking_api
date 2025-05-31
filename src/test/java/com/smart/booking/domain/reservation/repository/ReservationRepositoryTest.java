package com.smart.booking.domain.reservation.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.member.repository.MemberRepository;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.entity.ReservationMember;
import com.smart.booking.domain.reservation.enums.ReservationStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Reservation reservation;

    @BeforeEach
    @Transactional
    void setUp() {
        final Member member = Member.builder()
            .type(MemberType.USER)
            .build();

        // 테스트용 회원 데이터 생성
        memberRepository.save(member);

        final ReservationMember reservationMember = new ReservationMember(member, "테스트", "01036010559");
        // 테스트 전에 데이터 초기화
        reservation = new Reservation("1", null, 12345678, null, LocalDate.now(), "01", "02", ReservationStatus.RESERVED, reservationMember, "111", "A1",
            BigDecimal.valueOf(5000));
        reservationRepository.save(reservation); // 데이터베이스에 예약 저장
    }

    @Test
    @DisplayName("예약번호로_검색")
    @Transactional
    void testFindByReservationNumber() {
        // 예약 번호로 검색
        Optional<Reservation> found = reservationRepository.findByReservationNo(12345678);

        // 결과 검증
        assertTrue(found.isPresent());
    }
}