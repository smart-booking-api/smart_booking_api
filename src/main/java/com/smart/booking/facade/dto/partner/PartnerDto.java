package com.smart.booking.facade.dto.partner;

import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.entity.PartnerCompany;
import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.partner.value_object.BankAccount;
import com.smart.booking.domain.partner.value_object.CompanyManager;
import com.smart.booking.domain.partner.value_object.CompanyRepresentative;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class PartnerDto {

    @NotNull
    private final String id;
    @NotNull
    private final PartnerType type;
    @NotNull
    private final String loginId;
    @NotNull
    private final String code;
    @NotNull
    private final String email;
    @NotNull
    private final String phoneNumber;

    private final PartnerDtoCompany company;

    public PartnerDto(@NonNull Partner partner) {
        this.id = partner.getId();
        this.type = partner.getType();
        this.loginId = partner.getLoginId();
        this.code = partner.getCode();
        this.email = partner.getEmail();
        this.phoneNumber = partner.getPhoneNumber();
        this.company = new PartnerDtoCompany(partner.getCompany());
    }

    @Getter
    public static class PartnerDtoCompany {

        @NotNull
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

        public PartnerDtoCompany(@NotNull PartnerCompany partnerCompany) {
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
}
