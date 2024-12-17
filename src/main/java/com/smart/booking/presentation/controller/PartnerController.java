package com.smart.booking.presentation.controller;

import com.smart.booking.facade.partner.partner.ChangePartnerPasswordFacade;
import com.smart.booking.facade.partner.partner.DeletePartnerFacade;
import com.smart.booking.facade.partner.partner.GetPartnerFacade;
import com.smart.booking.facade.partner.partner.GetPartnersFacade;
import com.smart.booking.facade.partner.partner.GetPartnersFacade.GetPartnersRequestDto;
import com.smart.booking.presentation.controller.endPoint.PartnerEndPoint;
import com.smart.booking.presentation.security.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PartnerController {

    private final GetPartnerFacade getPartnerFacade;
    private final ChangePartnerPasswordFacade changePartnerPasswordFacade;
    private final GetPartnersFacade getPartnersFacade;
    private final DeletePartnerFacade deletePartnerFacade;

    @Operation(summary = "내 정보 조회")
    @GetMapping(PartnerEndPoint.ME)
    public GetPartnerFacade.GetPartnerResponse me() {
        return getPartnerFacade.execute(SecurityUtils.getCurrentMemberContext());
    }

    @Operation(summary = "비밀번호 변경")
    @PutMapping(PartnerEndPoint.CHANGE_PASSWORD)
    public ChangePartnerPasswordFacade.ChangePartnerPasswordResponse changePassword(
            @RequestBody @Valid ChangePartnerPasswordFacade.ChangePartnerPasswordRequestDto changePartnerPasswordRequestDto
    ) {
        return changePartnerPasswordFacade.execute(SecurityUtils.getCurrentMemberContext(), changePartnerPasswordRequestDto);
    }

    @Operation(summary = "파트너 목록 조회")
    @GetMapping(PartnerEndPoint.PARTNERS)
    public GetPartnersFacade.GetPartnersResponse getPartners(
        @Parameter(name = "query") @NonNull GetPartnersRequestDto getPartnersRequestDto
    ) {
        return getPartnersFacade.execute(getPartnersRequestDto, SecurityUtils.getCurrentMemberContext());
    }

    @Operation(summary = "마스터 파트너 삭제")
    @DeleteMapping(PartnerEndPoint.DELETE_PARTNER)
    public DeletePartnerFacade.DeletePartnerResponse deletePartner(
        @PathVariable String partnerId
    ) {
        return deletePartnerFacade.execute(partnerId, SecurityUtils.getCurrentMemberContext());
    }

    @Operation(summary = "마스터 파트너 조회")
    @GetMapping(PartnerEndPoint.PARTNER)
    public GetPartnerFacade.GetPartnerResponse getPartner(
        @PathVariable String partnerId
    ) {
        return getPartnerFacade.execute(partnerId, SecurityUtils.getCurrentMemberContext());
    }
}
