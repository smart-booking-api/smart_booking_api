package com.smart.booking.presentation.controller;

import com.smart.booking.facade.partner.store.CreateStoreFacade;
import com.smart.booking.facade.partner.store.GetMyStoreFacade;
import com.smart.booking.facade.partner.store.GetPartnerStoreFacade;
import com.smart.booking.presentation.controller.endPoint.PartnerStoreEndPoint;
import com.smart.booking.presentation.security.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PartnerStoreController {

    private final CreateStoreFacade createStoreFacade;
    private final GetPartnerStoreFacade getPartnerStoreFacade;
    private final GetMyStoreFacade getMyStoreFacade;

    @Operation(summary = "매장 생성 Api")
    @PostMapping(PartnerStoreEndPoint.CREATE_STORE)
    public @NonNull CreateStoreFacade.CreateStoreResultDto createStore(
        @RequestBody @Valid @NonNull CreateStoreFacade.CreateStoreRequestDto requestDto
    ) {
        return createStoreFacade.execute(requestDto);
    }


    @Operation(summary = "매장 조회 Api")
    @GetMapping(PartnerStoreEndPoint.GET_STORE)
    public @NonNull GetPartnerStoreFacade.GetPartnerStoreResponse getStore(
        @PathVariable @NonNull String storeId
    ) {
        return getPartnerStoreFacade.execute(storeId);
    }


    @Operation(summary = "내 매장 조회 Api")
    @GetMapping(PartnerStoreEndPoint.MY_STORE)
    public @NonNull GetMyStoreFacade.GetMyStoreResponse getMyStore() {
        return getMyStoreFacade.execute(SecurityUtils.getCurrentMemberContext());
    }


}
