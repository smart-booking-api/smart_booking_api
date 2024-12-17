package com.smart.booking.domain.partner.service;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.common.util.PasswordUtil;
import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.partner.dto.*;
import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.partner.mapper.PartnerMapper;
import com.smart.booking.domain.partner.repository.PartnerRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.smart.booking.common.enums.ResponseCode.*;

@RequiredArgsConstructor
@Service
class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;
    private final PasswordUtil passwordUtil;

    @Override
    public @NonNull Partner createPartner(@NonNull CreatePartnerDto createPartnerDto) {
        final String encodedPassword = passwordUtil.encodePassword(createPartnerDto.password());
        final Partner partner = PartnerMapper.toPartner(createPartnerDto.copyWithPassword(encodedPassword));

        return partnerRepository.save(partner);
    }

    @Override
    public @NonNull Partner initializePartner(@NonNull String partnerId, @NonNull InitializePartnerDto initializePartnerDto) {

        final Partner partner = getPartner(partnerId);

        if (partner.isInitialized()) {
            throw new CommonException(ALREADY_INITIALIZED_PARTNER);
        }

        if (partnerRepository.existsByBusinessRegistration(initializePartnerDto.businessRegistration())) {
            throw new CommonException(DUPLICATE_PARTNER_BUSINESS_REGISTRATION);
        }

        partner.initialize(
                initializePartnerDto.businessRegistration(),
                PartnerMapper.toPartnerCompany(initializePartnerDto.upsertPartnerCompanyDto())
        );

        return partnerRepository.save(partner);
    }

    @Override
    public @NonNull Partner updatePartner(@NonNull String partnerId, @NonNull UpdatePartnerDto updatePartnerDto) {
        final Partner partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new CommonException(NOT_FOUND_PARTNER));

        if (!partner.isInitialized()) {
            throw new CommonException(NOT_INITIALIZED_PARTNER);
        }

        partner.changePhoneNumber(updatePartnerDto.phoneNumber());
        partner.getCompany().update(updatePartnerDto.upsertPartnerCompanyDto());

        return partnerRepository.save(partner);
    }

    @Override
    public @NonNull Partner getPartner(@NonNull String id) {
        return partnerRepository.findById(id)
                .orElseThrow(() -> new CommonException(NOT_FOUND_PARTNER));
    }


    @Override
    public @NonNull CursorResult<Partner> getPartners(@NonNull GetPartnersDto getPartnersDto) {
        return partnerRepository.findByTypeAndCompanyNameWithCursor(
                getPartnersDto.type(),
                getPartnersDto.companyName(),
                getPartnersDto.cursor(),
                getPartnersDto.pageSize()
        );
    }

    @Override
    public @NonNull Optional<Partner> getPartnerByLoginId(@NonNull String loginId) {
        return partnerRepository.findByLoginId(loginId);
    }

    @Override
    public @NonNull Optional<Partner> getPartnerByMember(@NonNull Member member) {
        return partnerRepository.findByMember(member);
    }

    @Override
    public @NonNull Partner getPartnerByMemberIdOrThrow(@NonNull String memberId) {
        return partnerRepository.findByMember_Id(memberId)
                .orElseThrow(() -> new CommonException(NOT_FOUND_PARTNER));
    }

    @Override
    public @NonNull Partner getPartnerByMemberOrThrow(@NonNull Member member) {
        return partnerRepository.findByMember(member)
                .orElseThrow(() -> new CommonException(NOT_FOUND_PARTNER));
    }

    @Override
    public void withdrawPartner(@NonNull String id) {
        final Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new CommonException(NOT_FOUND_PARTNER));

        partnerRepository.delete(partner);
    }

    @Override
    public long getPartnerCount() {
        return partnerRepository.count();
    }

    @Override
    public @NonNull PartnerType getPartnerTypeByMember(@NonNull Member member) {
        return getPartnerByMemberOrThrow(member).getType();
    }

    @Override
    public @NonNull PartnerType getPartnerTypeByMemberId(@NonNull String memberId) {
        return partnerRepository.findByMember_Id(memberId)
                .map(Partner::getType)
                .orElseThrow(() -> new CommonException(NOT_FOUND_PARTNER));
    }

    @Override
    public void changePassword(@NonNull Member member, @NonNull ChangePartnerPasswordDto changePartnerPasswordDto) {
        final Partner partner = getPartnerByMemberOrThrow(member);
        final boolean isPasswordMatched = passwordUtil.matches(changePartnerPasswordDto.getPassword(), partner.getPassword());

        if (!isPasswordMatched) {
            throw new CommonException(ResponseCode.NOT_MATCHED_PARTNER_PASSWORD);
        }

        final String encodedNewPassword = passwordUtil.encodePassword(changePartnerPasswordDto.getNewPassword());

        partner.changePassword(encodedNewPassword);

        partnerRepository.save(partner);
    }

    @Override
    public @NonNull Partner findByMemberId(@NonNull String memberId) {
        return partnerRepository.findByMember_Id(memberId).orElseThrow(() -> new CommonException(NOT_FOUND_PARTNER));
    }

    @Override
    public void deletePartner(@NonNull String partnerId) {
        partnerRepository.deleteById(partnerId);
    }

}
