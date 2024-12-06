package com.smart.booking.facade.dto.partner;


import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.enums.PartnerType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PartnerDto {

    @NotNull
    private final String id;
    @NotNull
    private final String loginId;
    @NotNull
    private final PartnerType type;
    @NotNull
    private final String code;
    @NotNull
    private final String companyName;
    @NotNull
    private final String companyAddress;

    public PartnerDto(@NonNull Partner partner) {
        this.id = partner.getId();
        this.loginId = partner.getLoginId();
        this.type = partner.getType();
        this.code = partner.getCode();
        this.companyName = partner.getCompany().getName();
        this.companyAddress = partner.getCompany().getAddress();
    }
}
