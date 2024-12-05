package com.smart.booking.domain.store.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import com.smart.booking.domain.common.entity.BusinessRegistration;
import com.smart.booking.domain.common.enums.Region;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.OffsetDateTime;

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

    @PreRemove
    public void preRemove() {
        this.businessRegistration = null;
    }

}
