package com.smart.booking.facade.partner.store;

import com.smart.booking.common.dto.CommonEmptyResponse;
import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.domain.store.service.StorePartnerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class DeleteStoreFacade {

    private final MemberService memberService;
    private final PartnerService partnerService;
    private final StorePartnerService storePartnerService;

    @Transactional
    public @NonNull DeleteStoreResponse execute(
            @NonNull String storeId,
            @NonNull MemberContextDto memberContextDto
    ) {
        final var member = memberService.getMemberByIdOrThrow(memberContextDto.getMemberId());
        final var partnerType = partnerService.getPartnerTypeByMember(member);

        if (partnerType != PartnerType.M) {
            throw new CommonException(ResponseCode.NOT_PERMITTED_PARTNER_TYPE);
        }

        storePartnerService.deleteStore(storeId);

        return new DeleteStoreResponse();
    }


    public static class DeleteStoreResponse extends CommonEmptyResponse {
    }
}
