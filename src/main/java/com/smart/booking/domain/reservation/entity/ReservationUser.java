package com.smart.booking.domain.reservation.entity;

import com.smart.booking.domain.user.entity.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.Comment;

@Embeddable
public class ReservationUser {
    @Comment("이용자 id")
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Comment("예약자 이름")
    private String reservationUserName;
    @Comment("예약자 휴대폰 번호")
    private String reservationUserPhone;
}
