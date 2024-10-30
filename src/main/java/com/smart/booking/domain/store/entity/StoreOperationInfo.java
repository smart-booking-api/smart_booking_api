package com.smart.booking.domain.store.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import com.smart.booking.domain.store.value_object.StoreTrialOperation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
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
    @Enumerated(EnumType.STRING)
    @Column(name = "open_days")
    private List<DayOfWeek> openDays = new ArrayList<>();


    public boolean isTrialOperation(OffsetDateTime dateTime) {
        return trialOperation != null && trialOperation.isTrialOperation(dateTime);
    }

}
