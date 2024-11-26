package com.smart.booking.domain.store.service;

import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.repository.StoreClosedDayRepository;
import com.smart.booking.domain.store.repository.StoreRepository;
import java.util.List;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
class StoreUserServiceImpl extends StoreCommonServiceImpl implements StoreUserService {

    private final StoreRepository storeRepository;

    public StoreUserServiceImpl(@NonNull StoreRepository storeRepository, @NonNull StoreClosedDayRepository storeClosedDayRepository) {
        super(storeRepository, storeClosedDayRepository);
        this.storeRepository = storeRepository;
    }

    @Override
    public @NonNull List<Store> getStoresByRegion(Region region) {
        return storeRepository.findByRegion(region);
    }


}
