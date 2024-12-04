package com.smart.booking.facade.partner.store;


import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.service.StorePartnerService;
import com.smart.booking.facade.dto.store.PartnerStoreDto;
import com.smart.booking.facade.dto.store.TeeBoxFeeDto;
import com.smart.booking.facade.dto.store.UpsertStoreRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CreateStoreFacade {

    private final StorePartnerService storePartnerService;


    @Transactional
    public @NonNull CreateStoreResultDto execute(@NonNull CreateStoreRequestDto createStoreRequestDto) {
        final Store store = storePartnerService.createStore(createStoreRequestDto.toUpsertStoreDto());

        return new CreateStoreResultDto(store);
    }


    public static class CreateStoreRequestDto extends UpsertStoreRequestDto {

        public CreateStoreRequestDto(
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
            super(
                name,
                address,
                businessRegistrationNumber,
                businessRegistrationCode,
                openTime,
                closeTime,
                trialEndAt,
                discountRate,
                teeBoxFees,
                openWeekDays,
                memo
            );
        }

    }

    @Schema
    public static class CreateStoreResultDto extends CommonResponse<PartnerStoreDto> {

        public CreateStoreResultDto(@NonNull Store store) {
            super(new PartnerStoreDto(store));
        }

    }


}
