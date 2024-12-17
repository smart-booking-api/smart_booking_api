package com.smart.booking.facade.partner.partner;

import com.smart.booking.common.dto.CommonEmptyResponse;
import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.domain.partner.dto.InitializePartnerDto;
import com.smart.booking.domain.partner.dto.UpsertPartnerCompanyDto;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.facade.dto.partner.UpdatePartnerRequestDto;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InitializePartnerFacade {

    private final PartnerService partnerService;

    @Transactional
    public InitPartnerResponse execute(
            @NonNull InitializePartnerFacade.InitRequestPartnerRequestDto initPartnerRequestDto,
            @NonNull MemberContextDto memberContextDto
    ) {
        final String partnerId = partnerService.getPartnerByMemberIdOrThrow(memberContextDto.getMemberId()).getId();

        partnerService.initializePartner(partnerId, initPartnerRequestDto.toInitializePartnerDto());

        return new InitPartnerResponse();

    }

    @Getter
    @RequiredArgsConstructor
    public static class InitRequestPartnerRequestDto extends UpdatePartnerRequestDto {
        public InitializePartnerDto toInitializePartnerDto() {
            return InitializePartnerDto.builder()
                    .businessRegistration(getBusinessRegistration())
                    .upsertPartnerCompanyDto(
                            UpsertPartnerCompanyDto.builder()
                                    .name(getCompany().getName())
                                    .address(getCompany().getAddress())
                                    .fax(getCompany().getFax())
                                    .businessType(getCompany().getBusinessType())
                                    .businessCategory(getCompany().getBusinessCategory())
                                    .etaxEmail(getCompany().getEtaxEmail())
                                    .bankAccount(getCompany().getBankAccount())
                                    .representative(getCompany().getRepresentative())
                                    .manager(getCompany().getManager())
                                    .memo(getCompany().getMemo())
                                    .build()
                    )
                    .build();
        }

    }

    public static class InitPartnerResponse extends CommonEmptyResponse {
    }


}
