package com.smart.booking.facade.partner.store;


import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.domain.store.dto.UpsertStoreDto;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.service.StorePartnerService;
import com.smart.booking.facade.dto.store.PartnerStoreDto;
import com.smart.booking.facade.dto.store.TeeBoxFeeDto;
import com.smart.booking.facade.dto.store.UpsertStoreRequestDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.List;


@RequiredArgsConstructor
@Component
public class UpdateStoreFacade {

    private final PartnerService partnerService;
    private final StorePartnerService storePartnerService;


    @Transactional
    public @NonNull UpdateStoreResponse execute(
            @NonNull String storeId,
            @NonNull UpdateStoreRequestDto updateStoreRequestDto,
            @NonNull MemberContextDto memberContextDto
    ) {

        final PartnerType partnerType = partnerService.getPartnerTypeByMemberId(memberContextDto.getMemberId());

        if (partnerType != PartnerType.M) {
            throw new CommonException(ResponseCode.NOT_PERMITTED_PARTNER_TYPE);
        }

        final Store store = storePartnerService.updateStore(storeId, updateStoreRequestDto.toUpsertStoreDto());

        return new UpdateStoreResponse(new PartnerStoreDto(store));
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


    public static class UpdateStoreResponse extends CommonResponse<PartnerStoreDto> {

        public UpdateStoreResponse(@NonNull PartnerStoreDto partnerStoreDto) {
            super(partnerStoreDto);
        }

    }

}
