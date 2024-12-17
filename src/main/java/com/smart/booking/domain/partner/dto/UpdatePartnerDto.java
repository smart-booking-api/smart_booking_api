package com.smart.booking.domain.partner.dto;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record UpdatePartnerDto(
        @NonNull String loginId,
        @NonNull String password,
        @NonNull String phoneNumber,
        @NonNull UpsertPartnerCompanyDto upsertPartnerCompanyDto
) {

}
