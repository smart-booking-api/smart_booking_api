package com.smart.booking.domain.store.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import com.smart.booking.domain.store.value_object.StoreTrialOperation;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "store_operation_info")
public class StoreOperationInfo extends BaseEntity {

    @Id
    @TsidGenerator
    private String id;

    @OneToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    private String openTime;

    private String closeTime;

    private StoreTrialOperation trialOperation;

    private String memo;

    @Builder.Default
    @OneToMany(mappedBy = "storeOperationInfo")
    private List<StoreTeeBoxFee> teeBoxFees = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "storeOperationInfo", cascade = CascadeType.ALL)
    private List<StoreOpenDayOfWeek> openDayOfWeeks = new ArrayList<>();


    public boolean isTrialOperation(OffsetDateTime dateTime) {
        return trialOperation != null && trialOperation.isTrialOperation(dateTime);
    }

}
