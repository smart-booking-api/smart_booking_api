package com.smart.booking.domain.partner.service;

import static com.smart.booking.common.enums.ResponseCode.NOT_FOUND_PARTNER;

import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.partner.dto.CreatePartnerDto;
import com.smart.booking.domain.partner.dto.GetPartnersDto;
import com.smart.booking.domain.partner.dto.InitializePartnerDto;
import com.smart.booking.domain.partner.dto.UpdatePartnerDto;
import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.mapper.PartnerMapper;
import com.smart.booking.domain.partner.repository.PartnerRepository;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;

    @Override
    public @NonNull Partner createPartner(@NonNull CreatePartnerDto createPartnerDto) {
        return partnerRepository.save(PartnerMapper.toPartner(createPartnerDto));
    }

    @Override
    public @NonNull Partner initializePartner(InitializePartnerDto initializePartnerDto) throws CommonException {
        final Partner partner = getPartner(initializePartnerDto.partnerId());
        partner.updateCompany(PartnerMapper.toPartnerCompany(initializePartnerDto.upsertPartnerCompanyDto()));

        return partnerRepository.save(partner);
    }

    @Override
    public @NonNull Partner updatePartner(@NonNull UpdatePartnerDto updatePartnerDto) throws CommonException {
        final Partner partner = getPartner(updatePartnerDto.partnerId());
        partner.changePhoneNumber(updatePartnerDto.phoneNumber());
        partner.updateCompany(PartnerMapper.toPartnerCompany(updatePartnerDto.upsertPartnerCompanyDto()));

        return partnerRepository.save(partner);
    }

    @Override
    public @NonNull Partner getPartner(@NonNull String id) throws CommonException {
        return partnerRepository.findById(id)
            .orElseThrow(() -> new CommonException(NOT_FOUND_PARTNER));
    }

    @Override
    public @NonNull CursorResult<Partner> getPartners(@NonNull GetPartnersDto getPartnersDto) {
        return null;
    }

    @Override
    public @NonNull Optional<Partner> getPartnerByLoginId(@NonNull String loginId) {
        return partnerRepository.findByLoginId(loginId);
    }

    @Override
    public @NonNull Optional<Partner> getPartnerByMember(@NonNull Member member) {
        return partnerRepository.findByMember(member);
    }
}
