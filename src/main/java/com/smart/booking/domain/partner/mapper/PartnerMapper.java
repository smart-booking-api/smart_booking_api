package com.smart.booking.domain.partner.mapper;

import com.smart.booking.domain.partner.dto.CreatePartnerDto;
import com.smart.booking.domain.partner.dto.UpsertPartnerCompanyDto;
import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.entity.PartnerCompany;
import lombok.NonNull;

public interface PartnerMapper {

    static @NonNull Partner toPartner(@NonNull CreatePartnerDto createPartnerDto) {
        return Partner.builder()
            .type(createPartnerDto.type())
            .code(createPartnerDto.code())
            .phoneNumber(createPartnerDto.phoneNumber())
            .password(createPartnerDto.password())
            .build();
    }

    static @NonNull PartnerCompany toPartnerCompany(@NonNull UpsertPartnerCompanyDto upsertPartnerCompanyDto) {
        return PartnerCompany.builder()
            .name(upsertPartnerCompanyDto.name())
            .address(upsertPartnerCompanyDto.address())
            .fax(upsertPartnerCompanyDto.fax())
            .businessType(upsertPartnerCompanyDto.businessType())
            .businessCategory(upsertPartnerCompanyDto.businessCategory())
            .etaxEmail(upsertPartnerCompanyDto.etaxEmail())
            .bankAccount(upsertPartnerCompanyDto.bankAccount())
            .representative(upsertPartnerCompanyDto.representative())
            .manager(upsertPartnerCompanyDto.manager())
            .memo(upsertPartnerCompanyDto.memo())
            .build();
    }
}
