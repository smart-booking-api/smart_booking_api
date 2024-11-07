package com.smart.booking.domain.partner.service;

import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.partner.dto.CreatePartnerDto;
import com.smart.booking.domain.partner.dto.GetPartnersDto;
import com.smart.booking.domain.partner.dto.InitializePartnerDto;
import com.smart.booking.domain.partner.dto.UpdatePartnerDto;
import com.smart.booking.domain.partner.entity.Partner;
import java.util.Optional;
import lombok.NonNull;

public interface PartnerService {

    @NonNull Partner createPartner(@NonNull CreatePartnerDto createPartnerDto);

    @NonNull Partner initializePartner(@NonNull InitializePartnerDto initializePartnerDto) throws CommonException;

    @NonNull Partner updatePartner(@NonNull UpdatePartnerDto updatePartnerDto) throws CommonException;

    @NonNull Partner getPartner(@NonNull String id) throws CommonException;

    @NonNull CursorResult<Partner> getPartners(@NonNull GetPartnersDto getPartnersDto);

    @NonNull Optional<Partner> getPartnerByLoginId(@NonNull String loginId);

    @NonNull Optional<Partner> getPartnerByMember(@NonNull Member member);

    void withdrawPartner(@NonNull String id) throws CommonException;
}
