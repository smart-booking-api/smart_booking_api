package com.smart.booking.domain.partner.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import com.smart.booking.domain.partner.value_object.BankAccount;
import com.smart.booking.domain.partner.value_object.CompanyManager;
import com.smart.booking.domain.partner.value_object.CompanyRepresentative;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "partner_company")
public class PartnerCompany extends BaseEntity {

    @Id
    @TsidGenerator
    private String id;

    @OneToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    private String name;

    private String address;

    private String fax;

    private String businessType;

    private String businessCategory;

    private String etaxEmail;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "bankName", column = @Column(name = "bank_name")),
        @AttributeOverride(name = "number", column = @Column(name = "bank_account_number")),
        @AttributeOverride(name = "holder", column = @Column(name = "bank_account_holder")),
    })
    private BankAccount bankAccount;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "representative_name")),
        @AttributeOverride(name = "phoneNumber", column = @Column(name = "representative_phone_number")),
    })
    private CompanyRepresentative representative;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "manager_name")),
        @AttributeOverride(name = "email", column = @Column(name = "manager_email")),
        @AttributeOverride(name = "phoneNumber", column = @Column(name = "manager_phone_number")),
        @AttributeOverride(name = "position", column = @Column(name = "manager_position")),
    })
    private CompanyManager manager;

    private String memo;
    

}
