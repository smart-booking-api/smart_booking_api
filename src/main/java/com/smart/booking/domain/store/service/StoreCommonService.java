package com.smart.booking.domain.store.service;

import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.store.entity.Store;
import java.util.List;
import lombok.NonNull;


public interface StoreCommonService {

    @NonNull Store getStoreById(String storeId) throws CommonException;

    @NonNull List<Region> getRegions();
}
