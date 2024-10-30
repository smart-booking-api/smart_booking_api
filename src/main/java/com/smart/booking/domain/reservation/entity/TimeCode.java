package com.smart.booking.domain.reservation.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TimeCode extends BaseEntity {
    @Id
    @TsidGenerator
    private String id;
    private String timeCode;
}
