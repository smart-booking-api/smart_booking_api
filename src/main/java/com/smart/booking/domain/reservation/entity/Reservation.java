package com.smart.booking.domain.reservation.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import com.smart.booking.domain.reservation.enums.ReservationStatus;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity {
    @Id
    @TsidGenerator
    private String id;
    @Comment("매장")
    @ManyToOne
    @JoinColumn(name= "store_id")
    private Store store;
    @Comment("예약번호")
    private String bookingNo;
    @Comment("타석")
    @ManyToOne
    @JoinColumn(name= "box_id")
    private TeeBox box;
    @Comment("예약일")
    private OffsetDateTime reservationDate;
    @Comment("예약 시작시간")
    private String startTimeCode;
    @Comment("예약 종료시간")
    private String endTimeCode;
    @Comment("예약상태")
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;
    @Embedded
    private ReservationMember reservationMember;
    private String trackingId;
}
