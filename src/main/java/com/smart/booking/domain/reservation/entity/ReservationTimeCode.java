package com.smart.booking.domain.reservation.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Time;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationTimeCode {
    @Id
    @TsidGenerator
    private String id;

    @Comment("시간")
    private Time time;

    @Comment("시간코드명")
    private String timeName;
}
