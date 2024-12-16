package com.smart.booking.facade.partner.partner;

import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.common.dto.MemberContext;
import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.partner.dto.GetPartnersDto;
import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.facade.dto.partner.PartnerDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class GetPartnersFacade {

    private final PartnerService partnerService;

    @Transactional(readOnly = true)
    public GetPartnersResponse execute(
        @NonNull GetPartnersRequestDto getPartnersRequestDto,
        @NonNull MemberContext memberContext
    ) {

        final CursorResult<Partner> partners = partnerService.getPartners(getPartnersRequestDto.toGetPartnersDto());

        return new GetPartnersResponse(new PartnersDto(partners));
    }


    @Getter
    @RequiredArgsConstructor
    public static class GetPartnersRequestDto {

        private final PartnerType type;
        private final String companyName;
        @Schema(defaultValue = "20")
        private final Integer pageSize;
        private final String cursor;

        public int getPageSize() {
            return pageSize == null ? 20 : pageSize;
        }

        public @NonNull GetPartnersDto toGetPartnersDto() {
            return new GetPartnersDto(
                type,
                companyName,
                getPageSize(),
                cursor
            );
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class GetPartnersResponse extends CommonResponse<PartnersDto> {

        public GetPartnersResponse(@NonNull PartnersDto partnersDto) {
            super(partnersDto);
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class PartnersDto {

        @NonNull
        private final List<PartnerDto> list;
        private final boolean hasNext;
        private final int totalCount;


        public PartnersDto(@NonNull CursorResult<Partner> cursorResult) {
            this.list = cursorResult.content().stream().map(PartnerDto::new).toList();
            this.hasNext = cursorResult.hasNext();
            this.totalCount = cursorResult.totalCount();
        }
    }


}
