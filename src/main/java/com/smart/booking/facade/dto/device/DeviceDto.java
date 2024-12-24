package com.smart.booking.facade.dto.device;


import com.smart.booking.domain.device.entity.Device;
import com.smart.booking.domain.device.enums.DeviceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeviceDto {

    @NotNull
    private final String id;

    @NotNull
    private final DeviceStatus status;

    public DeviceDto(@NonNull Device device) {
        this.id = device.getId();
        this.status = device.getStatus();
    }

}
