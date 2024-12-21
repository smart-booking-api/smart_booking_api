package com.smart.booking.domain.device.dto;

import com.smart.booking.domain.common.entity.BusinessRegistration;
import com.smart.booking.domain.device.enums.DeviceStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpsertRedGolfDeviceDto {
    
    private final DeviceStatus status;

    private final BusinessRegistration businessRegistration;

    private final String storeCode;

    private final String clientId;
}
