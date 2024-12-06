package com.smart.booking.facade.partner.partner;

import com.smart.booking.common.dto.CommonEmptyResponse;
import com.smart.booking.common.dto.MemberContext;
import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.auth.service.AuthService;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.partner.service.PartnerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class DeletePartnerFacade {

    private final AuthService authService;
    private final MemberService memberService;
    private final PartnerService partnerService;


    @Transactional
    public DeletePartnerResponse execute(
        @NonNull String partnerId,
        @NonNull MemberContext memberContext
    ) {

        final Member member = memberService.getMemberById(memberContext.getMemberId());
        final Partner me = partnerService.getPartnerByMemberOrThrow(member);

        if (me.getType() != PartnerType.M) {
            throw new CommonException(ResponseCode.NOT_PERMITTED_PARTNER_TYPE);
        }

        final Partner partner = partnerService.getPartner(partnerId);

        partnerService.deletePartner(partner.getId());
        authService.deleteRefreshTokenByMember(partner.getMember());
        memberService.deleteMember(partner.getMember());

        return new DeletePartnerResponse();
    }

    public static class DeletePartnerResponse extends CommonEmptyResponse {

    }
}
