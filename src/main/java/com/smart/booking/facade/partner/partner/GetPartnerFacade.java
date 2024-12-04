package com.smart.booking.facade.partner.partner;

import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.common.dto.MemberContext;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.facade.dto.partner.PartnerDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class GetPartnerFacade {

    private final MemberService memberService;
    private final PartnerService partnerService;


    @Transactional(readOnly = true)
    public GetPartnerResponse execute(@NonNull MemberContext memberContext) {
        final Member member = memberService.getMemberById(memberContext.getMemberId());
        final Partner partner = partnerService.getPartnerByMemberOrThrow(member);
        return new GetPartnerResponse(partner);
    }

    public static class GetPartnerResponse extends CommonResponse<PartnerDto> {

        GetPartnerResponse(@NonNull Partner partner) {
            super(new PartnerDto(partner));
        }

    }

}
