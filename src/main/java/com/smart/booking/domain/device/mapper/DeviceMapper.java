package com.smart.booking.domain.device.mapper;

import com.smart.booking.domain.device.dto.UpsertRedGolfDeviceDto;
import com.smart.booking.domain.device.entity.RedGolfDevice;
import lombok.NonNull;

public interface DeviceMapper {

    @NonNull
    static RedGolfDevice toRedGolfDevice(@NonNull UpsertRedGolfDeviceDto upsertRedGolfDeviceDto) {
        return RedGolfDevice.builder()
            .status(upsertRedGolfDeviceDto.getStatus())
            .businessRegistration(upsertRedGolfDeviceDto.getBusinessRegistration())
            .storeCode(upsertRedGolfDeviceDto.getStoreCode())
            .clientId(upsertRedGolfDeviceDto.getClientId())
            .build();
    }
}
