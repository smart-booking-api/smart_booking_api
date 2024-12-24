package com.smart.booking.domain.reservation.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.smart.booking.common.util.CommonUtil;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.member.repository.MemberRepository;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.entity.ReservationMember;
import com.smart.booking.domain.reservation.enums.ReservationStatus;
import com.smart.booking.domain.reservation.repository.ReservationRepository;
import com.smart.booking.facade.dto.reservation.MonthlyReservationDto;
import com.smart.booking.facade.user.reservation.GetMonthlyReservationFacade;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserReservationServiceTest {
    @Autowired
    private UserReservationService userReservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private GetMonthlyReservationFacade getMonthlyReservationFacade;

    private Member member;

    @BeforeEach
    @Transactional
    void setUp() {
        member = memberService.createMember(MemberType.USER);
        memberRepository.save(member);
    }

    @Test
    //todo 연관관계가 많으면 테스트 어떻게?
    @Disabled
    void 같은날_예약이_여러개일때_예약완료_예약취소_이용완료_출력() {
        System.out.println(member.getId());

        // 예약완료
        reservationRepository.save(getReservation(member));
        // 예약취소
        reservationRepository.save(getCancelReservation(member));

        LocalDate today = LocalDate.now();
        String year = String.valueOf(today.getYear());
        String month = String.valueOf(today.getMonthValue());

        List<MonthlyReservationDto> reservationDtoList = getMonthlyReservationFacade.execute(year, month, member.getId());

        assertNull(reservationDtoList.stream().filter(item -> item.getReservationStatus() == ReservationStatus.CANCELED).findAny());
    }

    private Reservation getReservation(Member member) {
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

    private Reservation getCancelReservation(Member member) {
        var randomNumber = CommonUtil.createRandomNumber();
        ReservationMember reservationMember = new ReservationMember(member, "점보문어","01036010559");

        return Reservation.builder()
            .store(null)
            .reservationNo(randomNumber)
            .teeBox(null)
            .startTimeId("0J0N5S51W434J")
            .endTimeId("0J0N5S51W434J")
            .reservationMember(reservationMember)
            .reservationStatus(ReservationStatus.CANCELED)
            .reservationDate(LocalDate.now())
            .build();
    }
}