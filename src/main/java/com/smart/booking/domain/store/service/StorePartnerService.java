package com.smart.booking.domain.store.service;

import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.common.entity.BusinessRegistration;
import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.store.dto.GetStoresDto;
import com.smart.booking.domain.store.dto.ScheduleStoreClosedDaysDto;
import com.smart.booking.domain.store.dto.UpsertStoreDto;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.entity.StoreClosedDay;
import java.util.List;
import lombok.NonNull;

public interface StorePartnerService extends StoreCommonService {


    @NonNull Store getStoreByBusinessRegistration(@NonNull BusinessRegistration businessRegistration);

    @NonNull Store createStore(@NonNull UpsertStoreDto upsertStoreDto);

    @NonNull Store updateStore(@NonNull UpsertStoreDto upsertStoreDto) throws CommonException;

    void deleteStore(@NonNull String storeId);

    @NonNull CursorResult<Store> getStores(@NonNull GetStoresDto getStoresDto) throws CommonException;

    @NonNull List<StoreClosedDay> getStoreClosedDays(@NonNull Store store);

    @NonNull List<StoreClosedDay> scheduleClosedDays(@NonNull ScheduleStoreClosedDaysDto scheduleClosedDays);
}
