package com.smart.booking.domain.device.repository;

import com.smart.booking.domain.device.entity.RedGolfDevice;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedGolfDeviceRepository extends JpaRepository<RedGolfDevice, String> {

    Optional<RedGolfDevice> findByStoreCodeAndClientId(@NonNull String storeCode, @NonNull String clientId);

}
