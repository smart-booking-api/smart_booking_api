package com.smart.booking.facade.partner.reservation;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.reservation.service.PartnerReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CancelReservationFacade {
    private final PartnerReservationService partnerReservationService;
    private final MemberService memberService;

    public void execute(String reservationId, String memberId) {
        Member member = memberService.getMemberById(memberId);
        partnerReservationService.cancelReservation(reservationId, memberId, member.getType());
    }
}
