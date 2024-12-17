package com.smart.booking.facade.dto.partner;

import com.smart.booking.domain.common.entity.BusinessRegistration;
import com.smart.booking.domain.partner.dto.UpdatePartnerDto;
import com.smart.booking.domain.partner.dto.UpsertPartnerCompanyDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class UpdatePartnerRequestDto {
    @NotNull
    private String loginId;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String password;

    @NotNull
    private BusinessRegistration businessRegistration;

    @NotNull
    private PartnerCompanyDto company;


    public UpdatePartnerDto toUpdatePartnerDto() {
        return UpdatePartnerDto.builder()
                .loginId(getLoginId())
                .password(getPassword())
                .phoneNumber(getPhoneNumber())
                .upsertPartnerCompanyDto(
                        UpsertPartnerCompanyDto.builder()
                                .name(getCompany().getName())
                                .address(getCompany().getAddress())
                                .fax(getCompany().getFax())
                                .businessType(getCompany().getBusinessType())
                                .businessCategory(getCompany().getBusinessCategory())
                                .etaxEmail(getCompany().getEtaxEmail())
                                .bankAccount(getCompany().getBankAccount())
                                .representative(getCompany().getRepresentative())
                                .manager(getCompany().getManager())
                                .memo(getCompany().getMemo())
                                .build()
                )
                .build();
    }


}
