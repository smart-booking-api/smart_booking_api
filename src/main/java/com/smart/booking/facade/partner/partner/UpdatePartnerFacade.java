package com.smart.booking.facade.partner.partner;


import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.facade.dto.partner.UpdatePartnerRequestDto;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdatePartnerFacade {

    private final PartnerService partnerService;

    @Transactional
    public UpdatePartnerResponse execute(
            @NonNull String partnerId,
            @NonNull UpdatePartnerRequestDto updateRequestPartnerDto,
            @NonNull MemberContextDto memberContextDto
    ) {
        final PartnerType partnerType = partnerService.getPartnerTypeByMemberId(memberContextDto.getMemberId());

        if (partnerType != PartnerType.M) {
            throw new CommonException(ResponseCode.NOT_PERMITTED_PARTNER_TYPE);
        }

        partnerService.updatePartner(partnerId, updateRequestPartnerDto.toUpdatePartnerDto());

        return new UpdatePartnerResponse(partnerId);
    }

    public static class UpdatePartnerResponse extends CommonResponse<String> {

        public UpdatePartnerResponse(String partnerId) {
            super(partnerId);
        }
    }

}
