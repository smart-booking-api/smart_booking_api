package com.smart.booking.facade.partner.device;

import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.domain.device.entity.Device;
import com.smart.booking.domain.device.service.DeviceService;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.service.StorePartnerService;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.domain.tee_box.service.TeeBoxPartnerService;
import com.smart.booking.facade.dto.device.DeviceDto;
import java.util.List;
import java.util.Objects;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetUnlinkedDevicesFacade {

    private final StorePartnerService storePartnerService;
    private final TeeBoxPartnerService teeBoxPartnerService;
    private final DeviceService deviceService;

    @Transactional(readOnly = true)
    public GetUnlinkedDevicesResponse execute(@NonNull String storeId) {
        final Store store = storePartnerService.getStoreById(storeId);

        final List<Device> linkedDevices = teeBoxPartnerService.getTeeBoxesByStoreId(store.getId())
            .stream()
            .map(TeeBox::getDevice)
            .filter(Objects::nonNull)
            .toList();

        final List<Device> unlinkedDevices = deviceService.findDevicesByBusinessRegistrationOrThrow(store.getBusinessRegistration())
            .stream()
            .filter(device -> !linkedDevices.contains(device))
            .toList();

        return new GetUnlinkedDevicesResponse(unlinkedDevices.stream().map(DeviceDto::new).toList());
    }


    public static class GetUnlinkedDevicesResponse extends CommonResponse<List<DeviceDto>> {

        public GetUnlinkedDevicesResponse(@NonNull List<DeviceDto> data) {
            super(data);
        }
    }
}
