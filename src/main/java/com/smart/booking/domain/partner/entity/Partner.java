package com.smart.booking.domain.partner.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import com.smart.booking.domain.common.entity.BusinessRegistration;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.partner.enums.PartnerType;
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
@SQLDelete(sql = "UPDATE partner SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(name = "partner", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"type", "code"}),
        @UniqueConstraint(columnNames = {"business_registration_number", "business_registration_code"})
})
public class Partner extends BaseEntity {

    @Id
    @TsidGenerator
    private String id;

    @Enumerated(EnumType.STRING)
    private PartnerType type;

    @Column(unique = true)
    private String loginId;

    private String password;

    private String code;

    private String email;

    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "business_registration_number")),
            @AttributeOverride(name = "code", column = @Column(name = "business_registration_code")),
    })
    private BusinessRegistration businessRegistration;


    @OneToOne(cascade = CascadeType.ALL)
    private PartnerCompany company;

    private OffsetDateTime deletedAt;

    public void initialize(@NonNull BusinessRegistration businessRegistration, @NonNull PartnerCompany company) {
        this.businessRegistration = businessRegistration;
        company.changePartner(this);
        this.company = company;
    }

    public void changePhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void changePassword(@NonNull String password) {
        this.password = password;
    }

    public boolean isInitialized() {
        return company != null || businessRegistration != null;
    }


    @PreRemove
    public void preRemove() {
        this.loginId = null;
        this.password = null;
        this.code = null;
        this.company.changePartner(null);
        this.company = null;
    }

}
