package com.smart.booking.domain.store.dto;

import com.smart.booking.domain.common.entity.BusinessRegistration;
import com.smart.booking.domain.store.value_object.StoreTrialOperation;
import java.time.DayOfWeek;
import java.util.List;
import lombok.NonNull;

public record UpsertStoreDto(
    String id,
    @NonNull String name,
    @NonNull String address,
    @NonNull BusinessRegistration businessRegistration,
    @NonNull String openTime,
    @NonNull String closeTime,
    @NonNull StoreTrialOperation trialOperation,
    @NonNull List<UpsertStoreDtoTeeBoxFee> teeBoxFees,
    @NonNull List<DayOfWeek> openDays,
    @NonNull String memo
) {


}