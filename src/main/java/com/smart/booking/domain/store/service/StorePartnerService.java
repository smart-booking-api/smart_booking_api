package com.smart.booking.domain.store.service;

import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.store.dto.GetStoresDto;
import com.smart.booking.domain.store.dto.UpsertStoreDto;
import com.smart.booking.domain.store.entity.Store;
import lombok.NonNull;

public interface StorePartnerService extends StoreCommonService {

    @NonNull Store createStore(@NonNull UpsertStoreDto createStoreDto);

    @NonNull Store updateStore(@NonNull UpsertStoreDto createStoreDto) throws CommonException;

    void deleteStore(@NonNull String storeId);

    @NonNull CursorResult<Store> getStores(@NonNull GetStoresDto getStoresDto) throws CommonException;
}
