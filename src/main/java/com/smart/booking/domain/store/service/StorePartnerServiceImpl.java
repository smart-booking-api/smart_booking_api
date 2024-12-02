package com.smart.booking.domain.store.service;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.common.entity.BusinessRegistration;
import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.store.dto.GetStoresDto;
import com.smart.booking.domain.store.dto.ScheduleStoreClosedDaysDto;
import com.smart.booking.domain.store.dto.UpsertStoreDto;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.entity.StoreClosedDay;
import com.smart.booking.domain.store.mapper.StoreClosedDayMapper;
import com.smart.booking.domain.store.mapper.StoreMapper;
import com.smart.booking.domain.store.repository.RegionRepository;
import com.smart.booking.domain.store.repository.StoreClosedDayRepository;
import com.smart.booking.domain.store.repository.StoreRepository;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
class StorePartnerServiceImpl extends StoreCommonServiceImpl implements StorePartnerService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final StoreClosedDayRepository storeClosedDayRepository;

    public StorePartnerServiceImpl(
        @NonNull StoreRepository storeRepository,
        @NonNull RegionRepository regionRepository,
        @NonNull StoreClosedDayRepository storeClosedDayRepository
    ) {
        super(storeRepository, storeClosedDayRepository);
        this.storeRepository = storeRepository;
        this.regionRepository = regionRepository;
        this.storeClosedDayRepository = storeClosedDayRepository;
    }

    @Override
    public @NonNull Store getStoreByBusinessRegistration(@NonNull BusinessRegistration businessRegistration) {
        return null;
    }

    @Override
    public @NonNull Store createStore(@NonNull UpsertStoreDto upsertStoreDto) {
        final Region region = regionRepository.parseRegion(upsertStoreDto.address());

        if (storeRepository.existsByBusinessRegistration(upsertStoreDto.businessRegistration())) {
            throw new CommonException(ResponseCode.DUPLICATE_STORE_BUSINESS_REGISTRATION);
        }

        final Store store = StoreMapper.toStore(upsertStoreDto, region);

        return storeRepository.save(store);
    }

    @Override
    public @NonNull Store updateStore(@NonNull UpsertStoreDto upsertStoreDto) {

        if (upsertStoreDto.id() == null) {
            throw new CommonException(ResponseCode.NOT_FOUND_STORE);
        }

        final Store store = getStoreById(upsertStoreDto.id());

        final boolean isSameBusinessRegistration = store.getBusinessRegistration().equals(upsertStoreDto.businessRegistration());
        final boolean isDuplicateBusinessRegistration = storeRepository.existsByBusinessRegistration(upsertStoreDto.businessRegistration());
        
        if (isSameBusinessRegistration && isDuplicateBusinessRegistration) {
            throw new CommonException(ResponseCode.DUPLICATE_STORE_BUSINESS_REGISTRATION);
        }

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
    public @NonNull CursorResult<Store> getStores(@NonNull GetStoresDto getStoresDto) {
        return storeRepository.findByNameAndRegionWithCursor(
            getStoresDto.name(),
            getStoresDto.region(),
            getStoresDto.cursor(),
            getStoresDto.pageSize()
        );
    }

    @Override
    public @NonNull List<StoreClosedDay> getStoreClosedDays(@NonNull Store store) {
        return storeClosedDayRepository.findAllByStore(store);
    }

    /**
     * 매장 휴무일을 등록 및 삭제한다.
     * 엔티티 리스트에는 있고 날짜 목록에는 없다 => 삭제됨
     * 날짜에는 있고 엔티티 리스트에는 없다 => 생성
     *
     * @param scheduleClosedDaysDto 매장 휴무일 정보
     *
     * @return 휴무일 목록
     */
    @Override
    public @NonNull List<StoreClosedDay> scheduleClosedDays(@NonNull ScheduleStoreClosedDaysDto scheduleClosedDaysDto) {

        final List<StoreClosedDay> closedDays = storeClosedDayRepository.findAllByStoreAndClosedAtAfter(
            scheduleClosedDaysDto.store(),
            OffsetDateTime.now()
        );

        final var removalClosedDays = closedDays.stream()
            .filter(closedDay -> !scheduleClosedDaysDto.dates().contains(closedDay.getClosedAt()))
            .toList();

        final var newClosedDays = StoreClosedDayMapper.toStoreClosedDays(new ScheduleStoreClosedDaysDto(
                scheduleClosedDaysDto.store(),
                scheduleClosedDaysDto.dates().stream()
                    .filter(date -> closedDays.stream().map(StoreClosedDay::getClosedAt).noneMatch(date::equals))
                    .toList()
            )
        );

        storeClosedDayRepository.deleteAll(removalClosedDays);
        return storeClosedDayRepository.saveAll(newClosedDays);
    }

}
