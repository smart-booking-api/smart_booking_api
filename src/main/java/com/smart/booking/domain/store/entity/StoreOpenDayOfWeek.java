package com.smart.booking.domain.store.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "store_open_day_of_week", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"store_operation_info_id", "day_of_week"})
})
public class StoreOpenDayOfWeek extends BaseEntity {
    @Id
    @TsidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "store_operation_info_id")
    private StoreOperationInfo storeOperationInfo;


    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;
}


