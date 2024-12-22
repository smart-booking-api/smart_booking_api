package com.smart.booking.domain.reservation.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.smart.booking.common.util.CommonUtil;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.member.repository.MemberRepository;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.entity.ReservationMember;
import com.smart.booking.domain.reservation.enums.ReservationStatus;
import com.smart.booking.domain.reservation.repository.ReservationRepository;
import com.smart.booking.facade.dto.reservation.MonthlyReservation;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserReservationServiceTest {
    @Autowired
    private UserReservationService userReservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member(null, MemberType.USER);
        memberRepository.save(member);

        reservationRepository.save(getReservation());
    }

    @Test
    void 월별_예약조회() {
        LocalDate today = LocalDate.now();
        String year = String.valueOf(today.getYear());
        String month = String.valueOf(today.getMonthValue());

        List<Reservation> reservations = userReservationService.getMonthlyMyReservationHistory(member.getId(), year, month);
        Map<ReservationStatus, List<MonthlyReservation>> reservationMap = reservations.stream()
            .map(MonthlyReservation::new)
            .collect(Collectors.groupingBy(MonthlyReservation::getReservationStatus));

        assertNotNull(reservationMap.get(ReservationStatus.RESERVED));
    }

    private Reservation getReservation() {
        var randomNumber = CommonUtil.createRandomNumber();
        ReservationMember reservationMember = new ReservationMember(member, "점보문어","01036010559");

        return Reservation.builder()
            .store(null)
            .reservationNo(randomNumber)
            .teeBox(null)
            .startTimeId("0J0N5S4YM41JP")
            .endTimeId("0J0N5S51M40G6")
            .reservationMember(reservationMember)
            .reservationStatus(ReservationStatus.RESERVED)
            .reservationDate(LocalDate.now())
            .build();
    }
}