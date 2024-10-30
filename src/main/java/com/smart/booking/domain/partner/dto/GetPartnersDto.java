package com.smart.booking.domain.partner.dto;

import com.smart.booking.domain.partner.enums.PartnerType;

public record GetPartnersDto(
    PartnerType type,
    String companyName,
    int pageSize,
    String cursor
) {

}
