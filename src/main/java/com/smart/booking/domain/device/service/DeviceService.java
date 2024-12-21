package com.smart.booking.domain.device.service;

import com.smart.booking.domain.common.entity.BusinessRegistration;
import com.smart.booking.domain.device.dto.UpsertRedGolfDeviceDto;
import com.smart.booking.domain.device.entity.Device;
import com.smart.booking.domain.device.entity.RedGolfDevice;
import java.util.List;
import lombok.NonNull;

public interface DeviceService {

    @NonNull
    Device findDeviceByIdOrThrow(@NonNull String deviceId);

    @NonNull
    RedGolfDevice upsertRedGolfDevice(@NonNull UpsertRedGolfDeviceDto upsertRedGolfDeviceDto);

    @NonNull
    List<Device> findDevicesByBusinessRegistrationOrThrow(@NonNull BusinessRegistration businessRegistration);
}
