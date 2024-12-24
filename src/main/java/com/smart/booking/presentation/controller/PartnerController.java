package com.smart.booking.presentation.controller;

import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.common.resolver.MemberContext;
import com.smart.booking.facade.dto.partner.UpdatePartnerRequestDto;
import com.smart.booking.facade.partner.partner.*;
import com.smart.booking.facade.partner.partner.GetPartnersFacade.GetPartnersRequestDto;
import com.smart.booking.presentation.controller.endPoint.PartnerEndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "파트너 관리", description = "파트너 관리 컨트롤러")
public class PartnerController {

    private final GetPartnerFacade getPartnerFacade;
    private final ChangePartnerPasswordFacade changePartnerPasswordFacade;
    private final GetPartnersFacade getPartnersFacade;
    private final DeletePartnerFacade deletePartnerFacade;
    private final CreatePartnerFacade createPartnerFacade;
    private final InitializePartnerFacade initializePartnerFacade;
    private final UpdatePartnerFacade updatePartnerFacade;

    @Operation(summary = "내 정보 조회")
    @GetMapping(PartnerEndPoint.ME)
    public GetPartnerFacade.GetPartnerResponse me(@MemberContext MemberContextDto memberContextDto) {
        return getPartnerFacade.execute(memberContextDto);
    }

    @Operation(summary = "내 비밀번호 변경")
    @PutMapping(PartnerEndPoint.CHANGE_MY_PASSWORD)
    public ChangePartnerPasswordFacade.ChangePartnerPasswordResponse changePassword(
            @RequestBody @Valid ChangePartnerPasswordFacade.ChangePartnerPasswordRequestDto changePartnerPasswordRequestDto,
            @MemberContext MemberContextDto memberContextDto
    ) {
        return changePartnerPasswordFacade.execute(changePartnerPasswordRequestDto, memberContextDto);
    }

    @Operation(summary = "파트너 목록 조회")
    @GetMapping(PartnerEndPoint.PARTNERS)
    public GetPartnersFacade.GetPartnersResponse getPartners(
            @Parameter(name = "query") @NonNull GetPartnersRequestDto getPartnersRequestDto,
            @MemberContext MemberContextDto memberContextDto
    ) {
        return getPartnersFacade.execute(getPartnersRequestDto, memberContextDto);
    }

    @Operation(summary = "파트너 삭제")
    @DeleteMapping(PartnerEndPoint.DELETE_PARTNER)
    public DeletePartnerFacade.DeletePartnerResponse deletePartner(
            @PathVariable String partnerId,
            @MemberContext MemberContextDto memberContextDto
    ) {
        return deletePartnerFacade.execute(partnerId, memberContextDto);
    }

    @Operation(summary = "파트너 조회")
    @GetMapping(PartnerEndPoint.PARTNER)
    public GetPartnerFacade.GetPartnerResponse getPartner(
            @PathVariable String partnerId,
            @MemberContext MemberContextDto memberContextDto

    ) {
        return getPartnerFacade.execute(partnerId, memberContextDto);
    }

    @Operation(summary = "파트너 생성")
    @PostMapping(PartnerEndPoint.CREATE_PARTNER)
    public CreatePartnerFacade.CreatePartnerResponse createPartner(
            @RequestBody @Valid CreatePartnerFacade.CreatePartnerRequestDto createPartnerRequestDto,
            @MemberContext MemberContextDto memberContextDto
    ) {
        return createPartnerFacade.execute(createPartnerRequestDto, memberContextDto);
    }

    @Operation(summary = "파트너 초기화")
    @PostMapping(PartnerEndPoint.INIT_PARTNER)
    public InitializePartnerFacade.InitPartnerResponse initPartner(
            @RequestBody @Valid InitializePartnerFacade.InitRequestPartnerRequestDto initPartnerRequestDto,
            @MemberContext MemberContextDto memberContextDto
    ) {
        return initializePartnerFacade.execute(initPartnerRequestDto, memberContextDto);
    }

    @Operation(summary = "파트너 수정")
    @PutMapping(PartnerEndPoint.UPDATE_PARTNER)
    public UpdatePartnerFacade.UpdatePartnerResponse updatePartner(
            @PathVariable String partnerId,
            @RequestBody @Valid UpdatePartnerRequestDto updatePartnerRequestDto,
            @MemberContext MemberContextDto memberContextDto
    ) {
        return updatePartnerFacade.execute(partnerId, updatePartnerRequestDto, memberContextDto);
    }
}
