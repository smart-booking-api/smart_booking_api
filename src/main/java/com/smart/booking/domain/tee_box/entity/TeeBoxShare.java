package com.smart.booking.domain.tee_box.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import com.smart.booking.domain.partner.entity.Partner;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@SQLDelete(sql = "UPDATE tee_box_share SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(name = "tee_box_share")
public class TeeBoxShare extends BaseEntity {

    @Id
    @TsidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "tee_box_id")
    private TeeBox teeBox;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    private int share;
    
}
