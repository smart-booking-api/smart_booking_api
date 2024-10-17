package com.smart.booking.domain.tee_box.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import com.smart.booking.domain.common.enums.TeeBoxType;
import com.smart.booking.domain.device.entity.Device;
import com.smart.booking.domain.store.entity.Store;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Builder
@Entity
@Getter
@Setter
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

    @OneToOne
    @JoinColumn(name = "device_id")
    private Device device;

    private int number;

    private OffsetDateTime deletedAt;

    @OneToMany(mappedBy = "teeBox")
    private List<TeeBoxShare> shares;

    public String getName() {
        return this.type.getValue() + " #" + this.number;
    }


}
