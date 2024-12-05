package com.smart.booking.presentation.controller;

import com.smart.booking.facade.partner.store.*;
import com.smart.booking.presentation.controller.endPoint.PartnerStoreEndPoint;
import com.smart.booking.presentation.security.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PartnerStoreController {

    private final CreateStoreFacade createStoreFacade;
    private final GetPartnerStoreFacade getPartnerStoreFacade;
    private final GetMyStoreFacade getMyStoreFacade;
    private final GetStoresFacade getStoresFacade;
    private final DeleteStoreFacade deleteStoreFacade;

    @Operation(summary = "매장 생성")
    @PostMapping(PartnerStoreEndPoint.CREATE_STORE)
    public @NonNull CreateStoreFacade.CreateStoreResultDto createStore(
            @RequestBody @Valid @NonNull CreateStoreFacade.CreateStoreRequestDto requestDto
    ) {
        return createStoreFacade.execute(requestDto, SecurityUtils.getCurrentMemberContext());
    }

    @Operation(summary = "내 매장 조회")
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = @Content(schema = @Schema(implementation = GetMyStoreFacade.GetMyStoreResponse.class))
    )
    @GetMapping(PartnerStoreEndPoint.GET_MY_STORE)
    public @NonNull GetMyStoreFacade.GetMyStoreResponse getMyStore() {
        return getMyStoreFacade.execute(SecurityUtils.getCurrentMemberContext());
    }


    @Operation(summary = "매장 목록 조회")
    @GetMapping(PartnerStoreEndPoint.GET_STORES)
    public @NonNull GetStoresFacade.GetStoresResponse getStores(
            @Parameter(name = "query") GetStoresFacade.GetStoresRequestDto getStoresRequestDto
    ) {
        return getStoresFacade.execute(getStoresRequestDto);
    }

    @Operation(summary = "매장 조회")
    @GetMapping(PartnerStoreEndPoint.GET_STORE)
    public @NonNull GetPartnerStoreFacade.GetPartnerStoreResponse getStore(
            @PathVariable @NonNull String storeId
    ) {
        return getPartnerStoreFacade.execute(storeId);
    }

    @Operation(summary = "매장 삭제")
    @DeleteMapping(PartnerStoreEndPoint.DELETE_STORE)
    public @NonNull DeleteStoreFacade.DeleteStoreResponse deleteStore(
            @PathVariable @NonNull String storeId
    ) {
        return deleteStoreFacade.execute(storeId, SecurityUtils.getCurrentMemberContext());
    }
}
