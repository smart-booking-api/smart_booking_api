package com.smart.booking.facade.partner.partner;

import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.partner.service.PartnerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetPartnerCountFacade {

    private final MemberService memberService;
    private final PartnerService partnerService;

    public GetPartnerCountResponse execute(@NonNull MemberContextDto memberContextDto) {
        final Member member = this.memberService.getMemberByIdOrThrow(memberContextDto.getMemberId());
        final PartnerType partnerType = this.partnerService.getPartnerTypeByMember(member);

        if (!partnerType.equals(PartnerType.M)) {
            throw new CommonException(ResponseCode.NOT_PERMITTED_PARTNER_TYPE);
        }

        final long partnerCount = this.partnerService.getPartnerCount();
        return new GetPartnerCountResponse(partnerCount);
    }

    public static class GetPartnerCountResponse extends CommonResponse<Long> {

        public GetPartnerCountResponse(long partnerCount) {
            super(partnerCount);
        }
    }
}
