package com.smart.booking.presentation.controller;

import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.common.resolver.MemberContext;
import com.smart.booking.facade.dto.teebox.UpsertTeeBoxRequestDto;
import com.smart.booking.facade.partner.device.GetUnlinkedDevicesFacade;
import com.smart.booking.facade.partner.store.CreateStoreFacade;
import com.smart.booking.facade.partner.store.DeleteStoreFacade;
import com.smart.booking.facade.partner.store.GetMyStoreFacade;
import com.smart.booking.facade.partner.store.GetPartnerStoreFacade;
import com.smart.booking.facade.partner.store.GetStoresFacade;
import com.smart.booking.facade.partner.store.UpdateStoreFacade;
import com.smart.booking.facade.partner.teebox.CreateTeeBoxFacade;
import com.smart.booking.facade.partner.teebox.DeleteTeeBoxFacade;
import com.smart.booking.facade.partner.teebox.GetPartnerTeeBoxFacade;
import com.smart.booking.facade.partner.teebox.GetPartnerTeeBoxesFacade;
import com.smart.booking.facade.partner.teebox.UpdateTeeBoxFacade;
import com.smart.booking.presentation.controller.endPoint.PartnerStoreEndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "파트너 매장 관리", description = "파트너 매장 관리 컨트롤러")
public class PartnerStoreController {

    private final CreateStoreFacade createStoreFacade;
    private final GetPartnerStoreFacade getPartnerStoreFacade;
    private final GetMyStoreFacade getMyStoreFacade;
    private final GetStoresFacade getStoresFacade;
    private final DeleteStoreFacade deleteStoreFacade;
    private final UpdateStoreFacade updateStoreFacade;
    private final GetPartnerTeeBoxesFacade getPartnerTeeBoxesFacade;
    private final GetPartnerTeeBoxFacade getPartnerTeeBoxFacade;
    private final CreateTeeBoxFacade createTeeBoxFacade;
    private final UpdateTeeBoxFacade updateTeeBoxFacade;
    private final DeleteTeeBoxFacade deleteTeeBoxFacade;
    private final GetUnlinkedDevicesFacade getUnlinkedDevicesFacade;

    @Operation(summary = "매장 생성", description = "Master 파트너만 사용 가능")
    @PostMapping(PartnerStoreEndPoint.CREATE_STORE)
    public @NonNull CreateStoreFacade.CreateStoreResultDto createStore(
        @RequestBody @Valid @NonNull CreateStoreFacade.CreateStoreRequestDto requestDto,
        @MemberContext MemberContextDto memberContextDto
    ) {
        return createStoreFacade.execute(requestDto, memberContextDto);
    }

    @Operation(summary = "내 매장 조회")
    @GetMapping(PartnerStoreEndPoint.GET_MY_STORE)
    public @NonNull GetMyStoreFacade.GetMyStoreResponse getMyStore(@MemberContext MemberContextDto memberContextDto) {
        return getMyStoreFacade.execute(memberContextDto);
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

    @Operation(summary = "매장 수정", description = "Master 파트너만 사용 가능")
    @PutMapping(PartnerStoreEndPoint.UPDATE_STORE)
    public @NonNull UpdateStoreFacade.UpdateStoreResponse updateStore(
        @PathVariable @NonNull String storeId,
        @RequestBody @Valid @NonNull UpdateStoreFacade.UpdateStoreRequestDto updateStoreRequestDto,
        @MemberContext MemberContextDto memberContextDto
    ) {
        return updateStoreFacade.execute(storeId, updateStoreRequestDto, memberContextDto);
    }

    @Operation(summary = "매장 삭제", description = "Master 파트너만 사용 가능")
    @DeleteMapping(PartnerStoreEndPoint.DELETE_STORE)
    public @NonNull DeleteStoreFacade.DeleteStoreResponse deleteStore(
        @PathVariable @NonNull String storeId,
        @MemberContext MemberContextDto memberContextDto
    ) {
        return deleteStoreFacade.execute(storeId, memberContextDto);
    }

    @Operation(summary = "매장 내 타석 목록 조회")
    @GetMapping(PartnerStoreEndPoint.GET_TEE_BOXES)
    public @NonNull GetPartnerTeeBoxesFacade.GetPartnerTeeBoxesResponse getTeeBoxes(
        @PathVariable @NonNull String storeId
    ) {
        return getPartnerTeeBoxesFacade.execute(storeId);
    }

    @Operation(summary = "매장 내 타석 조회")
    @GetMapping(PartnerStoreEndPoint.GET_TEE_BOX)
    public @NonNull GetPartnerTeeBoxFacade.GetPartnerTeeBoxResponse getTeeBox(
        @PathVariable @NonNull String storeId,
        @PathVariable @NonNull String teeBoxId
    ) {
        return getPartnerTeeBoxFacade.execute(storeId, teeBoxId);
    }

    @Operation(summary = "매장 내 타석 성생", description = "Master 파트너만 사용 가능")
    @PostMapping(PartnerStoreEndPoint.CREATE_TEE_BOX)
    public @NonNull CreateTeeBoxFacade.CreateTeeBoxResponse createTeeBox(
        @PathVariable @NonNull String storeId,
        @RequestBody @Valid @NonNull UpsertTeeBoxRequestDto upsertTeeBoxRequestDto,

        @MemberContext MemberContextDto memberContextDto
    ) {
        return createTeeBoxFacade.execute(storeId, upsertTeeBoxRequestDto, memberContextDto);
    }

    @Operation(summary = "매장 내 타석 수정", description = "Master 파트너만 사용 가능")
    @PutMapping(PartnerStoreEndPoint.UPDATE_TEE_BOX)
    public @NonNull UpdateTeeBoxFacade.UpdateTeeBoxResponse updateTeeBox(
        @PathVariable @NonNull String storeId,
        @PathVariable @NonNull String teeBoxId,
        @RequestBody @Valid @NonNull UpsertTeeBoxRequestDto upsertTeeBoxRequestDto,
        @MemberContext MemberContextDto memberContextDto
    ) {
        return updateTeeBoxFacade.execute(storeId, teeBoxId, upsertTeeBoxRequestDto, memberContextDto);
    }

    @Operation(summary = "매장 내 타석 삭제", description = "Master 파트너만 사용 가능")
    @DeleteMapping(PartnerStoreEndPoint.DELETE_TEE_BOX)
    public @NonNull DeleteTeeBoxFacade.DeleteTeeBoxResponse deleteTeeBox(
        @PathVariable @NonNull String storeId,
        @PathVariable @NonNull String teeBoxId,
        @MemberContext MemberContextDto memberContextDto
    ) {
        return deleteTeeBoxFacade.execute(storeId, teeBoxId, memberContextDto);
    }

    @Operation(summary = "매장 내 연결되지 않은 디바이스 목록 조회")
    @GetMapping(PartnerStoreEndPoint.GET_UNLINKED_DEVICES)
    public @NonNull GetUnlinkedDevicesFacade.GetUnlinkedDevicesResponse getUnlinkedDevices(
        @PathVariable @NonNull String storeId
    ) {
        return getUnlinkedDevicesFacade.execute(storeId);
    }

}
