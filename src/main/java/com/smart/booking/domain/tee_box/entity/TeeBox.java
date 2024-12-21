package com.smart.booking.domain.tee_box.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import com.smart.booking.domain.common.enums.TeeBoxType;
import com.smart.booking.domain.device.entity.Device;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.tee_box.dto.UpdateTeeBoxDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.OffsetDateTime;
import java.util.List;
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
@SQLDelete(sql = "UPDATE tee_box SET deleted_at = current_timestamp, number = null WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(
    name = "tee_box",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"store_id", "number"})
    }
)
public class TeeBox extends BaseEntity {

    @Id
    @TsidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Enumerated(EnumType.STRING)
    private TeeBoxType type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private Device device;

    private Integer number;

    private String screenName;

    private String memo;

    private OffsetDateTime deletedAt;

    @Builder.Default
    @OneToMany(
        mappedBy = "teeBox",
        cascade = CascadeType.ALL
    )
    private List<TeeBoxShare> shares = List.of();

    public String getName() {
        return this.type.getValue() + " #" + this.number;
    }

    public void edit(@NonNull UpdateTeeBoxDto updateTeeBoxDto) {
        this.screenName = updateTeeBoxDto.screenName();
        this.number = updateTeeBoxDto.number();
        this.device = updateTeeBoxDto.device();
        this.type = updateTeeBoxDto.type();
    }

    public void updateShares(@NonNull List<TeeBoxShare> shares) {
        for (TeeBoxShare share : this.shares) {
            share.removeTeeBox();
        }

        for (TeeBoxShare share : shares) {
            share.updateTeeBox(this);
        }

        this.shares = shares;
    }

    @PreRemove
    public void preRemove() {
        this.number = null;
        this.device = null;
    }

}
