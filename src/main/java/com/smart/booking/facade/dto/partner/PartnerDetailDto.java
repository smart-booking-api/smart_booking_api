package com.smart.booking.facade.dto.partner;

import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.enums.PartnerType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class PartnerDetailDto {

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

    private final PartnerCompanyDto company;

    public PartnerDetailDto(@NonNull Partner partner) {
        this.id = partner.getId();
        this.type = partner.getType();
        this.loginId = partner.getLoginId();
        this.code = partner.getCode();
        this.email = partner.getEmail();
        this.phoneNumber = partner.getPhoneNumber();
        this.company = new PartnerCompanyDto(partner.getCompany());
    }


}
