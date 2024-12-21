package com.smart.booking.facade.dto.store;

import com.smart.booking.domain.common.entity.BusinessRegistration;
import com.smart.booking.domain.store.dto.UpsertStoreDto;
import com.smart.booking.domain.store.dto.UpsertStoreDtoTeeBoxFee;
import com.smart.booking.domain.store.value_object.StoreTrialOperation;
import lombok.Getter;
import lombok.NonNull;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
public abstract class UpsertStoreRequestDto {

    @NonNull
    private final String name;
    @NonNull
    private final String address;
    @NonNull
    private final String businessRegistrationNumber;

    @NonNull
    private final String businessRegistrationCode;

    @NonNull
    private final String openTime;
    @NonNull
    private final String closeTime;
    @NonNull
    private final OffsetDateTime trialEndAt;

    private final int discountRate;
    @NonNull
    private final List<TeeBoxFeeDto> teeBoxFees;
    @NonNull
    private final List<DayOfWeek> openWeekDays;
    @NonNull
    private final String memo;

    public UpsertStoreRequestDto(
            @NonNull String name,
            @NonNull String address,
            @NonNull String businessRegistrationNumber,
            @NonNull String businessRegistrationCode,
            @NonNull String openTime,
            @NonNull String closeTime,
            @NonNull OffsetDateTime trialEndAt,
            int discountRate,
            @NonNull List<TeeBoxFeeDto> teeBoxFees,
            @NonNull List<DayOfWeek> openWeekDays,
            @NonNull String memo
    ) {
        this.name = name;
        this.address = address;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.businessRegistrationCode = businessRegistrationCode;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.trialEndAt = trialEndAt;
        this.discountRate = discountRate;
        this.teeBoxFees = teeBoxFees;
        this.openWeekDays = openWeekDays;
        this.memo = memo;
    }

    public UpsertStoreDto toUpsertStoreDto() {
        return new UpsertStoreDto(
                getName(),
                getAddress(),
                BusinessRegistration.builder()
                        .number(getBusinessRegistrationNumber())
                        .code(getBusinessRegistrationCode())
                        .build(),
                getOpenTime(),
                getCloseTime(),
                StoreTrialOperation.builder()
                        .trialEndAt(getTrialEndAt())
                        .discountRate(getDiscountRate())
                        .build(),
                getTeeBoxFees().stream().map(
                        (teeBoxFee) -> new UpsertStoreDtoTeeBoxFee(
                                teeBoxFee.getTeeBoxType(),
                                teeBoxFee.getBookingFee(),
                                teeBoxFee.getUsageFee(),
                                teeBoxFee.getOnSiteFee()
                        )
                ).toList(),
                getOpenWeekDays(),
                getMemo()
        );
    }


}
