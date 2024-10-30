package com.smart.booking.domain.partner.dto;

import lombok.NonNull;

public record UpdatePartnerDto(
    @NonNull String partnerId,
    @NonNull String phoneNumber,
    @NonNull UpsertPartnerCompanyDto upsertPartnerCompanyDto
) {

}
