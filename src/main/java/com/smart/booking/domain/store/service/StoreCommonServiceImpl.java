package com.smart.booking.domain.store.service;

import static com.smart.booking.common.enums.ResponseCode.NOT_FOUND_STORE;

import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.repository.StoreRepository;
import java.util.Arrays;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class StoreCommonServiceImpl implements StoreCommonService {

    private final StoreRepository storeRepository;

    @Override
    public @NonNull Store getStoreById(String storeId) throws CommonException {
        return storeRepository.findById(storeId)
            .orElseThrow(() -> new CommonException(NOT_FOUND_STORE));
    }

    @Override
    public @NonNull List<Region> getRegions() {
        return Arrays.stream(Region.values()).toList();
    }
}
