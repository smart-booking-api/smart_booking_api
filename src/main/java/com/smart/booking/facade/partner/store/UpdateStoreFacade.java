package com.smart.booking.facade.partner.store;


import com.smart.booking.domain.store.dto.UpsertStoreDto;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.service.StorePartnerService;
import com.smart.booking.facade.dto.store.PartnerStoreDto;
import com.smart.booking.facade.dto.store.TeeBoxFeeDto;
import com.smart.booking.facade.dto.store.UpsertStoreRequestDto;
import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * api/partner/stores/{id} PUT
 * 매장 수정 엔드포인트
 */


@RequiredArgsConstructor
@Component
public class UpdateStoreFacade {

    private final StorePartnerService storePartnerService;


    @Transactional
    public @NonNull UpdateStoreFacade.UpdatePartnerStoreResultDto execute(@NonNull String id, @NonNull UpdateStoreRequestDto updateStoreRequestDto) {
        final Store store = storePartnerService.updateStore(updateStoreRequestDto.toUpsertStoreDto().copyWithId(id));

        return new UpdatePartnerStoreResultDto(store);
    }

    public static class UpdateStoreRequestDto extends UpsertStoreRequestDto {


        public UpdateStoreRequestDto(
            @NonNull String name,
            @NonNull String address,
            @NonNull String businessRegistrationNumber,
            @NonNull String businessRegistrationCode,
            @NonNull String openTime,
            @NonNull String closeTime,
            @NonNull OffsetDateTime trialEndAt,
            int discountRate,
            @NonNull List<TeeBoxFeeDto> upsertStoreRequestDtoTeeBoxFees,
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
                upsertStoreRequestDtoTeeBoxFees,
                openWeekDays,
                memo
            );
        }

        @Override
        public UpsertStoreDto toUpsertStoreDto() {
            return super.toUpsertStoreDto();
        }
    }


    public static class UpdatePartnerStoreResultDto extends PartnerStoreDto {

        public UpdatePartnerStoreResultDto(@NonNull Store store) {
            super(store);
        }

    }

}
