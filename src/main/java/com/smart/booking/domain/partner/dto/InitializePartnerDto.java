package com.smart.booking.domain.partner.dto;

import com.smart.booking.domain.common.entity.BusinessRegistration;
import lombok.NonNull;

public record InitializePartnerDto(
    @NonNull String partnerId,
    @NonNull BusinessRegistration businessRegistration,
    @NonNull UpsertPartnerCompanyDto upsertPartnerCompanyDto
) {

}
