package com.smart.booking.domain.store.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import com.smart.booking.domain.common.enums.TeeBoxType;
import com.smart.booking.domain.store.value_object.WeekdayWeekendFee;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "store_tee_box_fee",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"store_operation_info_id", "tee_box_type"})
    }
)
public class StoreTeeBoxFee extends BaseEntity {


    @Id
    @TsidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "store_operation_info_id")
    private StoreOperationInfo storeOperationInfo;

    @Enumerated(EnumType.STRING)
    private TeeBoxType teeBoxType;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "weekdayFee", column = @Column(name = "booking_weekday_fee")),
        @AttributeOverride(name = "weekendFee", column = @Column(name = "booking_weekend_fee")),
    })
    private WeekdayWeekendFee bookingFee;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "weekdayFee", column = @Column(name = "usage_weekday_fee")),
        @AttributeOverride(name = "weekendFee", column = @Column(name = "usage_weekend_fee")),
    })
    private WeekdayWeekendFee usageFee;

    private BigDecimal onSiteFee;

}
