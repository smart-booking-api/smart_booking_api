package com.smart.booking.domain.store.dto;

import com.smart.booking.domain.common.entity.BusinessRegistration;
import com.smart.booking.domain.store.value_object.StoreTrialOperation;
import lombok.NonNull;

import java.time.DayOfWeek;
import java.util.List;

public record UpsertStoreDto(
        @NonNull String name,
        @NonNull String address,
        @NonNull BusinessRegistration businessRegistration,
        @NonNull String openTime,
        @NonNull String closeTime,
        @NonNull StoreTrialOperation trialOperation,
        @NonNull List<UpsertStoreDtoTeeBoxFee> teeBoxFees,
        @NonNull List<DayOfWeek> openDayOfWeeks,
        @NonNull String memo
) {


}