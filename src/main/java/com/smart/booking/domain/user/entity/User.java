package com.smart.booking.domain.user.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.user.enums.UserStatus;
import com.smart.booking.domain.user.value_object.UserPolicyAgreement;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
import lombok.NonNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;


@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SQLDelete(sql = "UPDATE \"user\" SET deleted_at = current_timestamp, email = null, phone = null WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(name = "\"user\"")
public class User extends BaseEntity {

    @Id
    @TsidGenerator
    private String id;

    @OneToOne(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private ThirdPartyAccount thirdPartyAccount;


    @OneToOne(cascade = CascadeType.ALL)
    private UserProfile profile;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Embedded
    private UserPolicyAgreement policyAgreement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private UserStatus status;

    private OffsetDateTime accessedAt;

    private OffsetDateTime deletedAt;


    public void changeMyStore(Store store) {
        this.store = store;
    }

    public void changePolicyAgreement(UserPolicyAgreement policyAgreement) {
        this.policyAgreement = policyAgreement;
    }

    public void withdraw() {
        this.profile = null;
        this.email = null;
        this.phone = null;
        this.deletedAt = OffsetDateTime.now();
    }

    public void changeStatus(@NonNull UserStatus userStatus) {
        this.status = userStatus;
    }
//    public UserStatus getUserStatus() {
//        if (deletedAt != null) {
//            return UserStatus.WITHDRAWAL;
//        }
//
//        if (accessedAt.isBefore(OffsetDateTime.now().minusMonths(6))) {
//            return UserStatus.DORMANCY;
//        }
//
//        return UserStatus.ACTIVE;
//    }
}
