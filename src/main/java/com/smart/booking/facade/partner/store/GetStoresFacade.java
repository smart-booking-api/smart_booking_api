package com.smart.booking.facade.partner.store;

import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.store.dto.GetStoresDto;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.service.StorePartnerService;
import com.smart.booking.facade.dto.store.PartnerStoreDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetStoresFacade {

    private final StorePartnerService storePartnerService;

    @Transactional(readOnly = true)
    public @NonNull GetStoresResponse execute(@NonNull GetStoresFacade.GetStoresRequestDto getStoresRequestDto) {
        final CursorResult<Store> cursorResult = storePartnerService.getStores(getStoresRequestDto.toGetStoresDto());
        return new GetStoresResponse(new PartnerStoresDto(cursorResult));
    }

    public record GetStoresRequestDto(
            String name,
            Region region,
            String cursor,
            @Schema(defaultValue = "20") Integer pageSize
    ) {

        public GetStoresDto toGetStoresDto() {
            return new GetStoresDto(name, region, cursor, pageSize == null ? 20 : pageSize);
        }

    }

    @Getter
    @RequiredArgsConstructor
    public static class PartnerStoresDto {

        @NotNull
        private final List<PartnerStoreDto> list;
        private final boolean hasNext;
        private final int totalCount;

        public PartnerStoresDto(@NonNull CursorResult<Store> cursorResult) {
            this(
                    cursorResult.content().stream().map(PartnerStoreDto::new).toList(),
                    cursorResult.hasNext(),
                    cursorResult.totalCount()
            );

        }
    }

    @Getter
    @RequiredArgsConstructor
    public class GetStoresResponse extends CommonResponse<PartnerStoresDto> {

        public GetStoresResponse(PartnerStoresDto data) {
            super(data);
        }
    }

}
