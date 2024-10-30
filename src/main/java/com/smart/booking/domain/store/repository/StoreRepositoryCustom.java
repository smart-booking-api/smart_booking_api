package com.smart.booking.domain.store.repository;

import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.store.entity.Store;
import lombok.NonNull;

public interface StoreRepositoryCustom {

    @NonNull
    CursorResult<Store> findByNameAndRegionWithCursor(
        String name,
        Region region,
        int pageSize,
        String cursor
    );

}
