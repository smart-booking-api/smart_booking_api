package com.smart.booking.domain.partner.dto;

import com.smart.booking.domain.common.entity.BusinessRegistration;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record InitializePartnerDto(
        @NonNull BusinessRegistration businessRegistration,
        @NonNull UpsertPartnerCompanyDto upsertPartnerCompanyDto
) {

}
