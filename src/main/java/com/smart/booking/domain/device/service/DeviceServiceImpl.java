package com.smart.booking.domain.device.service;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.common.entity.BusinessRegistration;
import com.smart.booking.domain.device.dto.UpsertRedGolfDeviceDto;
import com.smart.booking.domain.device.entity.Device;
import com.smart.booking.domain.device.entity.RedGolfDevice;
import com.smart.booking.domain.device.mapper.DeviceMapper;
import com.smart.booking.domain.device.repository.DeviceRepository;
import com.smart.booking.domain.device.repository.RedGolfDeviceRepository;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final RedGolfDeviceRepository redGolfDeviceRepository;

    @Override
    public @NonNull Device findDeviceByIdOrThrow(@NonNull String deviceId) {
        return deviceRepository.findById(deviceId).orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_DEVICE));
    }

    @Override
    public @NonNull RedGolfDevice upsertRedGolfDevice(@NonNull UpsertRedGolfDeviceDto upsertRedGolfDeviceDto) {
        final RedGolfDevice redGolfDevice = redGolfDeviceRepository.findByStoreCodeAndClientId(
                upsertRedGolfDeviceDto.getStoreCode(),
                upsertRedGolfDeviceDto.getClientId()
            )
            .orElse(DeviceMapper.toRedGolfDevice(upsertRedGolfDeviceDto));

        redGolfDevice.changeStatus(upsertRedGolfDeviceDto.getStatus());

        return redGolfDeviceRepository.save(redGolfDevice);
    }

    @Override
    public @NonNull List<Device> findDevicesByBusinessRegistrationOrThrow(@NonNull BusinessRegistration businessRegistration) {
        return deviceRepository.findAllByBusinessRegistration(businessRegistration);
    }


}
