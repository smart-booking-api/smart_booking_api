package com.smart.booking.domain.partner.dto;

import lombok.NonNull;

public record InitializePartnerDto(
    @NonNull String partnerId,

    @NonNull UpsertPartnerCompanyDto upsertPartnerCompanyDto

) {

}
