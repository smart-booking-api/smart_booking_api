package com.smart.booking.presentation.controller;

import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.common.resolver.MemberContext;
import com.smart.booking.facade.partner.settlement.MasterPartnerSettlementFacade;
import com.smart.booking.facade.partner.settlement.MasterPartnerSettlementFacade.MasterPartnerSettlementFacadeRequest;
import com.smart.booking.facade.partner.settlement.MasterPartnerSettlementFacade.MasterPartnerSettlementFacadeResponse;
import com.smart.booking.facade.partner.settlement.PartnerSettlementFacade;
import com.smart.booking.facade.partner.settlement.PartnerSettlementFacade.PartnerSettlementFacadeRequest;
import com.smart.booking.facade.partner.settlement.PartnerSettlementFacade.PartnerSettlementFacadeResponse;
import com.smart.booking.facade.partner.settlement.PartnerTeeBoxSettlementFacade;
import com.smart.booking.facade.partner.settlement.PartnerTeeBoxSettlementFacade.PartnerTeeBoxSettlementFacadeRequest;
import com.smart.booking.facade.partner.settlement.PartnerTeeBoxSettlementFacade.PartnerTeeBoxSettlementFacadeResponse;
import com.smart.booking.presentation.controller.endPoint.SettlementEndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "파트너 정산", description = "파트너 정산 조회 컨트롤")
public class SettlementController {

    private final MasterPartnerSettlementFacade masterPartnerSettlementFacade;
    private final PartnerSettlementFacade partnerSettlementFacade;
    private final PartnerTeeBoxSettlementFacade partnerTeeBoxSettlementFacade;

    /**
     * 본사 전체 정산 정보 조회
     */
    @Operation(security = {@SecurityRequirement(name = "accessToken")}, summary = "본사 전체 정산 조회", description = "본사만 요청 가능한 전체 정산 조회를 한다.")
    @PostMapping(SettlementEndPoint.MASTER_SETTLEMENT_BY_PARTNER_URL)
    public MasterPartnerSettlementFacadeResponse getMasterSettlementByPartner(
        @PathVariable("memberId") String memberId,
        @Valid @RequestBody MasterPartnerSettlementFacadeRequest request) {
        return masterPartnerSettlementFacade.execute(memberId, request);
    }

    /**
     * 가* 파트너 별 정산 정보 조회
     */
    @Operation(security = {@SecurityRequirement(name = "accessToken")}, summary = "파트너별 정산 조회", description = "파트너별 정산 조회를 한다.")
    @PostMapping(SettlementEndPoint.SETTLEMENT_BY_PARTNER_URL)
    public PartnerSettlementFacadeResponse cancelPayment(
        @PathVariable("memberId") String memberId,
        @Valid @RequestBody PartnerSettlementFacadeRequest request,
        @MemberContext MemberContextDto memberContextDto
    ) {
        return partnerSettlementFacade.execute(memberId, request);
    }

    /**
     * 파트너 > 장비별 정산 정보 조회
     */
    @Operation(security = {@SecurityRequirement(name = "accessToken")}, summary = "파트너별 장비별 정산 조회", description = "파트너별 장비별 정산 조회를 한다.")
    @PostMapping(SettlementEndPoint.SETTLEMENT_BY_TEE_BOX_URL)
    public PartnerTeeBoxSettlementFacadeResponse preparePayment(
        @PathVariable("memberId") String memberId,
        @Valid @RequestBody PartnerTeeBoxSettlementFacadeRequest request,
        @MemberContext MemberContextDto memberContextDto) {
        return partnerTeeBoxSettlementFacade.execute(memberId, request);
    }

}
