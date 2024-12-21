package com.smart.booking.domain.device.repository;

import com.smart.booking.domain.common.entity.BusinessRegistration;
import com.smart.booking.domain.device.entity.Device;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, String> {

    @NonNull
    List<Device> findAllByBusinessRegistration(@NonNull BusinessRegistration businessRegistration);
}
