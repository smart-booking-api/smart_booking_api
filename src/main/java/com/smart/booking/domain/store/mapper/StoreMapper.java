package com.smart.booking.domain.store.mapper;

import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.store.dto.UpsertStoreDto;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.entity.StoreOperationInfo;
import com.smart.booking.domain.store.entity.StoreTeeBoxFee;
import java.util.stream.Collectors;
import lombok.NonNull;

public interface StoreMapper {

    static @NonNull Store toStore(
        UpsertStoreDto upsertStoreDto,
        Region region
    ) {
        return Store.builder()
            .id(upsertStoreDto.id())
            .name(upsertStoreDto.name())
            .region(region)
            .address(upsertStoreDto.address())
            .businessRegistration(upsertStoreDto.businessRegistration())
            .operationInfo(toStoreOperationInfo(upsertStoreDto))
            .build();
    }

    static @NonNull StoreOperationInfo toStoreOperationInfo(@NonNull UpsertStoreDto upsertStoreDto) {
        return StoreOperationInfo.builder()
            .openTime(upsertStoreDto.openTime())
            .closeTime(upsertStoreDto.closeTime())
            .trialOperation(upsertStoreDto.trialOperation())
            .teeBoxFees(
                upsertStoreDto.teeBoxFees().stream()
                    .map(fee -> StoreTeeBoxFee.builder()
                        .teeBoxType(fee.teeBoxType())
                        .bookingFee(fee.bookingFee())
                        .usageFee(fee.usageFee())
                        .onSiteFee(fee.onSiteFee())
                        .build()
                    )
                    .collect(Collectors.toList())
            )
            .openDayOfWeeks(upsertStoreDto.openDayOfWeeks())
            .memo(upsertStoreDto.memo())
            .build();
    }

}
