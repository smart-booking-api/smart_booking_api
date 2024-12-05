package com.smart.booking.presentation.controller;

import com.smart.booking.facade.partner.partner.ChangePartnerPasswordFacade;
import com.smart.booking.facade.partner.partner.GetPartnerFacade;
import com.smart.booking.presentation.controller.endPoint.PartnerEndPoint;
import com.smart.booking.presentation.security.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PartnerController {

    private final GetPartnerFacade getPartnerFacade;
    private final ChangePartnerPasswordFacade changePartnerPasswordFacade;

    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = @Content(schema = @Schema(implementation = GetPartnerFacade.GetPartnerResponse.class))
    )
    @GetMapping(PartnerEndPoint.ME)
    public GetPartnerFacade.GetPartnerResponse me() {
        return getPartnerFacade.execute(SecurityUtils.getCurrentMemberContext());
    }


    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = @Content(schema = @Schema(implementation = ChangePartnerPasswordFacade.ChangePartnerPasswordResponse.class))
    )
    @PutMapping(PartnerEndPoint.CHANGE_PASSWORD)
    public ChangePartnerPasswordFacade.ChangePartnerPasswordResponse changePassword(
            @RequestBody @Valid ChangePartnerPasswordFacade.ChangePartnerPasswordRequestDto changePartnerPasswordRequestDto
    ) {
        return changePartnerPasswordFacade.execute(SecurityUtils.getCurrentMemberContext(), changePartnerPasswordRequestDto);
    }

}
