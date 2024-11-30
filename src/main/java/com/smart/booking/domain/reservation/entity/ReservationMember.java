package com.smart.booking.domain.reservation.entity;

import com.smart.booking.domain.member.entity.Member;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationMember {
    @Comment("예약자 id")
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Comment("예약자 이름")
    private String reservationUserName;

    @Comment("예약자 휴대폰 번호")
    private String reservationUserPhone;
}
