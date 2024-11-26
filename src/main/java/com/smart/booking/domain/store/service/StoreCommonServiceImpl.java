package com.smart.booking.domain.store.service;

import static com.smart.booking.common.enums.ResponseCode.NOT_FOUND_STORE;

import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.repository.StoreClosedDayRepository;
import com.smart.booking.domain.store.repository.StoreRepository;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class StoreCommonServiceImpl implements StoreCommonService {

    private final StoreRepository storeRepository;
    private final StoreClosedDayRepository storeClosedDayRepository;

    @Override
    public @NonNull Store getStoreById(String storeId) {
        return storeRepository.findById(storeId)
            .orElseThrow(() -> new CommonException(NOT_FOUND_STORE));
    }

    @Override
    public @NonNull List<Region> getRegions() {
        return Arrays.stream(Region.values()).toList();
    }

    @Override
    public boolean isClosedDay(@NonNull Store store, @NonNull OffsetDateTime dateTime) {
        return storeClosedDayRepository.existsByStoreAndClosedAt(store, dateTime.with(LocalTime.MIDNIGHT));
    }


}
