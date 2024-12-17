package com.smart.booking.facade.partner.store;


import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.service.StorePartnerService;
import com.smart.booking.facade.dto.store.PartnerStoreDto;
import com.smart.booking.facade.dto.store.TeeBoxFeeDto;
import com.smart.booking.facade.dto.store.UpsertStoreRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CreateStoreFacade {

    private final MemberService memberService;
    private final PartnerService partnerService;
    private final StorePartnerService storePartnerService;


    @Transactional
    public @NonNull CreateStoreResultDto execute(
            @NonNull CreateStoreRequestDto createStoreRequestDto,
            @NonNull MemberContextDto memberContextDto
    ) {

        final var member = memberService.getMemberByIdOrThrow(memberContextDto.getMemberId());
        final var partnerType = partnerService.getPartnerTypeByMember(member);

        if (partnerType != PartnerType.M) {
            throw new CommonException(ResponseCode.NOT_PERMITTED_PARTNER_TYPE);
        }

        final var store = storePartnerService.createStore(createStoreRequestDto.toUpsertStoreDto());

        return new CreateStoreResultDto(store);
    }


    public static class CreateStoreRequestDto extends UpsertStoreRequestDto {

        public CreateStoreRequestDto(
                @NotNull String name,
                @NotNull String address,
                @NotNull String businessRegistrationNumber,
                @NotNull String businessRegistrationCode,
                @NotNull String openTime,
                @NotNull String closeTime,
                @NotNull OffsetDateTime trialEndAt,
                int discountRate,
                @NotNull List<TeeBoxFeeDto> teeBoxFees,
                @NotNull List<DayOfWeek> openWeekDays,
                @NotNull String memo
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
