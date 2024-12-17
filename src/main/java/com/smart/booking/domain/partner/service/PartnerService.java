package com.smart.booking.domain.partner.service;

import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.partner.dto.*;
import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.enums.PartnerType;
import lombok.NonNull;

import java.util.Optional;

public interface PartnerService {

    @NonNull
    Partner createPartner(@NonNull CreatePartnerDto createPartnerDto);

    @NonNull
    Partner initializePartner(@NonNull InitializePartnerDto initializePartnerDto);

    @NonNull
    Partner updatePartner(@NonNull UpdatePartnerDto updatePartnerDto);

    @NonNull
    Partner getPartner(@NonNull String id);

    @NonNull
    CursorResult<Partner> getPartners(@NonNull GetPartnersDto getPartnersDto);

    @NonNull
    Optional<Partner> getPartnerByLoginId(@NonNull String loginId);

    @NonNull
    Optional<Partner> getPartnerByMember(@NonNull Member member);

    @NonNull
    Partner getPartnerByMemberOrThrow(@NonNull Member member);

    void withdrawPartner(@NonNull String id);

    long getPartnerCount();

    @NonNull
    PartnerType getPartnerTypeByMember(@NonNull Member member);

    void changePassword(@NonNull Member member, @NonNull ChangePartnerPasswordDto changePartnerPasswordDto);

    @NonNull
    Partner findByMemberId(@NonNull String memberId);

    void deletePartner(@NonNull String partnerId);
}
