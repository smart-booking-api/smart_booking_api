package com.smart.booking.domain.store.repository;

import com.smart.booking.domain.common.enums.Region;
import java.util.Arrays;
import java.util.List;
import lombok.NonNull;

public interface RegionRepository {

    default @NonNull List<Region> findAll() {
        return Arrays.stream(Region.values()).toList();
    }

    Region parseRegion(@NonNull String address);

}
