package com.smart.booking.facade.partner.store;

import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.common.dto.MemberContext;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.service.StorePartnerService;
import com.smart.booking.facade.dto.store.PartnerStoreDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class GetMyStoreFacade {

    private final MemberService memberService;
    private final PartnerService partnerService;
    private final StorePartnerService storePartnerService;

    @Transactional(readOnly = true)
    public GetMyStoreResponse execute(@NonNull MemberContext memberContext) {
        final Member member = this.memberService.getMemberByIdOrThrow(memberContext.getMemberId());
        final Partner partner = this.partnerService.getPartnerByMemberOrThrow(member);
        final Store store = this.storePartnerService.getStoreByBusinessRegistration(
            partner.getBusinessRegistration()
        );

        return new GetMyStoreResponse(store);
    }


    public static class GetMyStoreResponse extends CommonResponse<PartnerStoreDto> {

        public GetMyStoreResponse(Store store) {
            super(new PartnerStoreDto(store));
        }
    }

}
