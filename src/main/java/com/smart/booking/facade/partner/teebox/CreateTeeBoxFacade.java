package com.smart.booking.facade.partner.teebox;

import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.device.entity.Device;
import com.smart.booking.domain.device.service.DeviceService;
import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.service.StorePartnerService;
import com.smart.booking.domain.tee_box.service.TeeBoxPartnerService;
import com.smart.booking.facade.dto.teebox.TeeBoxDto;
import com.smart.booking.facade.dto.teebox.UpsertTeeBoxRequestDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateTeeBoxFacade {

    private final PartnerService partnerService;
    private final StorePartnerService storePartnerService;
    private final TeeBoxPartnerService teeBoxPartnerService;
    private final DeviceService deviceService;

    @Transactional
    public CreateTeeBoxResponse execute(
        @NonNull String storeId,
        @NonNull UpsertTeeBoxRequestDto upsertTeeBoxRequestDto,
        @NonNull MemberContextDto memberContextDto
    ) {
        final PartnerType partnerType = partnerService.getPartnerTypeByMemberId(memberContextDto.getMemberId());

        if (partnerType != PartnerType.M) {
            throw new CommonException(ResponseCode.NOT_PERMITTED_PARTNER_TYPE);
        }

        final Store store = storePartnerService.getStoreById(storeId);
        final Device device = deviceService.findDeviceByIdOrThrow(upsertTeeBoxRequestDto.getDeviceId());

        return new CreateTeeBoxResponse(new TeeBoxDto(teeBoxPartnerService.createTeeBox(upsertTeeBoxRequestDto.toCreateTeeBoxDto(store, device))));
    }


    public static class CreateTeeBoxResponse extends CommonResponse<TeeBoxDto> {

        public CreateTeeBoxResponse(@NonNull TeeBoxDto data) {
            super(data);
        }
    }

}
