package com.smart.booking.domain.store.service;

import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.store.entity.Store;
import java.util.List;
import lombok.NonNull;

public interface StoreUserService extends StoreCommonService {

    @NonNull List<Store> getStoresByRegion(Region region);

}
