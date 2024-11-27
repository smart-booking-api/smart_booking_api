package com.smart.booking.domain.partner.dto;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.partner.enums.PartnerType;
import lombok.NonNull;

public record CreatePartnerDto(
    @NonNull Member member,
    @NonNull PartnerType type,
    @NonNull String code,
    @NonNull String phoneNumber,
    @NonNull String password
) {


    public @NonNull CreatePartnerDto copyWithPassword(@NonNull String password) {
        return new CreatePartnerDto(member, type, code, phoneNumber, password);
    }
}
