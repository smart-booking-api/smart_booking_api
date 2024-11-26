package com.smart.booking.domain.store.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.OffsetDateTime;
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
@Table(name = "store_closed_day",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"store_id", "closed_at"})
    }
)
public class StoreClosedDay extends BaseEntity {

    @Id
    @TsidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private OffsetDateTime closedAt;

}
