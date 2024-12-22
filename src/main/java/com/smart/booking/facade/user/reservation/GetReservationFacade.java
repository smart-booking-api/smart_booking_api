package com.smart.booking.facade.user.reservation;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.facade.dto.reservation.ReservationSimpleResponseDto;
import com.smart.booking.facade.mapper.ReservationMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetReservationFacade {
    private final MemberService memberService;
    private final UserReservationService userReservationService;

    public List<ReservationSimpleResponseDto> getMyReservations(String memberId, String startDate) {
        Member member = memberService.getMemberById(memberId);
        List<Reservation> reservationList = userReservationService.getMyReservations(member, startDate);
        return ReservationMapper.INSTANCE.reservationsToDtoList(reservationList);
    }
}
