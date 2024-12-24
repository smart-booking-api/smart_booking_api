package com.smart.booking.facade.dto.store;

import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.common.enums.TeeBoxType;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.value_object.WeekdayWeekendFee;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;


// 파트너
@Getter
@Schema(description = "파트너 스토어")
@RequiredArgsConstructor
public class PartnerStoreDto {

    @NotNull
    private final String id;
    @NotNull
    private final String name;
    @NotNull
    private final Region region;
    @NotNull
    private final String address;
    @NotNull
    private final String businessRegistrationNumber;
    @NotNull
    private final String businessRegistrationCode;
    @NotNull
    private final String openTime;
    @NotNull
    private final String closeTime;
    @NotNull
    private final OffsetDateTime trialEndAt;
    @NotNull
    private final int discountRate;
    @NotNull
    private final List<TeeBoxFeeDto> teeBoxFees;
    
    private final String memo;

    public PartnerStoreDto(@NonNull Store store) {
        this(
                store.getId(),
                store.getName(),
                store.getRegion(),
                store.getAddress(),
                store.getBusinessRegistration().getNumber(),
                store.getBusinessRegistration().getCode(),
                store.getOperationInfo().getOpenTime(),
                store.getOperationInfo().getCloseTime(),
                store.getOperationInfo().getTrialOperation().getTrialEndAt(),
                store.getOperationInfo().getTrialOperation().getDiscountRate(),
                store.getOperationInfo().getTeeBoxFees().stream().map(
                        teeBoxFee -> new TeeBoxFeeDto(
                                teeBoxFee.getTeeBoxType(),
                                WeekdayWeekendFee.builder()
                                        .weekdayFee(teeBoxFee.getBookingFee().getWeekdayFee())
                                        .weekendFee(teeBoxFee.getBookingFee().getWeekendFee())
                                        .build(),
                                WeekdayWeekendFee.builder()
                                        .weekdayFee(teeBoxFee.getUsageFee().getWeekdayFee())
                                        .weekendFee(teeBoxFee.getUsageFee().getWeekendFee())
                                        .build(),
                                teeBoxFee.getOnSiteFee()
                        )
                ).toList(),
                store.getOperationInfo().getMemo()
        );

    }

    public record PartnerStoreDtoTeeBoxFee(
            TeeBoxType teeBoxType,
            int bookingWeekdayFee,
            int bookingWeekendFee,
            int usageWeekdayFee,
            int usageWeekendFee,
            int onSiteFee
    ) {


    }


}
