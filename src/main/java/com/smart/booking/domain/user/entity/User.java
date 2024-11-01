package com.smart.booking.domain.user.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.store.entity.Store;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
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
@SQLDelete(sql = "UPDATE \"user\" SET deleted_at = current_timestamp, email = null, phone = null WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(name = "\"user\"")
public class User extends BaseEntity {

    @Id
    @TsidGenerator
    private String id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private boolean pushAgreement;

    private boolean emailAgreement;

    private boolean smsAgreement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private String name;

    private String password;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private OffsetDateTime accessedAt;

    private OffsetDateTime deletedAt;
}
