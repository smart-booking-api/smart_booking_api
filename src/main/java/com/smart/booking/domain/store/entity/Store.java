package com.smart.booking.domain.store.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import com.smart.booking.domain.common.entity.BusinessRegistration;
import com.smart.booking.domain.common.enums.Region;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SQLDelete(sql = "UPDATE store SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(
    name = "store",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"business_registration_number", "business_registration_code"})
    }
)
public class Store extends BaseEntity {

    @Id
    @TsidGenerator
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Region region;

    private String address;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "number", column = @Column(name = "business_registration_number")),
        @AttributeOverride(name = "code", column = @Column(name = "business_registration_code")),
    })
    private BusinessRegistration businessRegistration;

    @OneToOne
    private StoreOperationInfo operationInfo;
    
    private OffsetDateTime deletedAt;


    public void update(
        @NonNull String name,
        @NonNull Region region,
        @NonNull String address,
        @NonNull BusinessRegistration businessRegistration,
        @NonNull StoreOperationInfo operationInfo
    ) {
        this.name = name;
        this.region = region;
        this.address = address;
        this.businessRegistration = businessRegistration;
        this.operationInfo = operationInfo;
    }

}
