package com.smart.booking.domain.store.service;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.store.dto.GetStoresDto;
import com.smart.booking.domain.store.dto.UpsertStoreDto;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.mapper.StoreMapper;
import com.smart.booking.domain.store.repository.RegionRepository;
import com.smart.booking.domain.store.repository.StoreRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
class StorePartnerServiceImpl extends StoreCommonServiceImpl implements StorePartnerService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    public StorePartnerServiceImpl(
        @NonNull StoreRepository storeRepository,
        @NonNull RegionRepository regionRepository
    ) {
        super(storeRepository);
        this.storeRepository = storeRepository;
        this.regionRepository = regionRepository;
    }

    @Override
    public @NonNull Store createStore(@NonNull UpsertStoreDto upsertStoreDto) {
        final Region region = regionRepository.parseRegion(upsertStoreDto.address());
        final Store store = StoreMapper.toStore(upsertStoreDto, region);

        return storeRepository.save(store);
    }

    @Override
    public @NonNull Store updateStore(@NonNull UpsertStoreDto upsertStoreDto) throws CommonException {

        if (upsertStoreDto.id() == null) {
            throw new CommonException(ResponseCode.NOT_FOUND_STORE);
        }

        final Store store = getStoreById(upsertStoreDto.id());

        final Region region = regionRepository.parseRegion(upsertStoreDto.address());

        store.update(
            upsertStoreDto.name(),
            region,
            upsertStoreDto.address(),
            upsertStoreDto.businessRegistration(),
            StoreMapper.toStoreOperationInfo(upsertStoreDto)
        );
        
        return storeRepository.save(store);
    }

    @Override
    public void deleteStore(@NonNull String storeId) {
        storeRepository.deleteById(storeId);
    }

    @Override
    public @NonNull CursorResult<Store> getStores(@NonNull GetStoresDto getStoresDto) throws CommonException {
        return storeRepository.findByNameAndRegionWithCursor(
            getStoresDto.name(),
            getStoresDto.region(),
            getStoresDto.pageSize(),
            getStoresDto.cursor()
        );
    }

}
