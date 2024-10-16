package com.smart.booking.domain.store.service;

import com.smart.booking.domain.store.dto.UpsertStoreDto;
import com.smart.booking.domain.store.entity.Store;
import lombok.NonNull;

public interface StorePartnerService extends StoreCommonService {

    @NonNull Store createStore(@NonNull UpsertStoreDto upsertStoreDto);

    @NonNull Store updateStore(@NonNull String storeId, @NonNull UpsertStoreDto upsertStoreDto);

    void deleteStore(@NonNull String storeId);


}
