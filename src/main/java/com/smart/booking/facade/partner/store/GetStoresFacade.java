package com.smart.booking.facade.partner.store;

import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.store.dto.GetStoresDto;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.service.StorePartnerService;
import com.smart.booking.facade.dto.store.PartnerStoreDto;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class GetStoresFacade {

    private final StorePartnerService storePartnerService;

    @Transactional(readOnly = true)
    public @NonNull GetStoresResultDto execute(@NonNull GetStoresFacade.GetStoresRequestDto getStoresRequestDto) {
        final CursorResult<Store> cursorResult = storePartnerService.getStores(getStoresRequestDto.toGetStoresDto());
        return new GetStoresResultDto(
            cursorResult.content().stream().map(PartnerStoreDto::new).toList(),
            cursorResult.hasNext(),
            cursorResult.totalCount()
        );
    }

    public record GetStoresRequestDto(
        String name,
        Region region,
        String cursor
    ) {

        public GetStoresDto toGetStoresDto() {
            return new GetStoresDto(name, region, cursor, 20);
        }

    }

    public record GetStoresResultDto(
        @NonNull List<PartnerStoreDto> list,
        boolean hasNext,
        int totalCount
    ) {

    }

}
