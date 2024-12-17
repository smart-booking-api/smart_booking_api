package com.smart.booking.facade.dto.partner;

import com.smart.booking.domain.partner.entity.PartnerCompany;
import com.smart.booking.domain.partner.value_object.BankAccount;
import com.smart.booking.domain.partner.value_object.CompanyManager;
import com.smart.booking.domain.partner.value_object.CompanyRepresentative;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class PartnerCompanyDto {

    private final String name;

    private final String address;

    private final String fax;

    private final String businessType;

    private final String businessCategory;

    private final String etaxEmail;

    private final BankAccount bankAccount;

    private final CompanyRepresentative representative;

    private final CompanyManager manager;

    private final String memo;

    public PartnerCompanyDto(@NotNull PartnerCompany partnerCompany) {
        name = partnerCompany.getName();
        address = partnerCompany.getAddress();
        fax = partnerCompany.getFax();
        businessType = partnerCompany.getBusinessType();
        businessCategory = partnerCompany.getBusinessCategory();
        etaxEmail = partnerCompany.getEtaxEmail();
        bankAccount = partnerCompany.getBankAccount();
        representative = partnerCompany.getRepresentative();
        manager = partnerCompany.getManager();
        memo = partnerCompany.getMemo();
    }
}
