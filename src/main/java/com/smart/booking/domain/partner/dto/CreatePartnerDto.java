package com.smart.booking.domain.partner.dto;

import com.smart.booking.domain.partner.enums.PartnerType;
import lombok.NonNull;

public record CreatePartnerDto(
    @NonNull PartnerType type,
    @NonNull String code,
    @NonNull String phoneNumber
) {

}
