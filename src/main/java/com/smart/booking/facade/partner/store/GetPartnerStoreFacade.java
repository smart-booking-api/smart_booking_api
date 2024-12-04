package com.smart.booking.facade.partner.store;

import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.service.StorePartnerService;
import com.smart.booking.facade.dto.store.PartnerStoreDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class GetPartnerStoreFacade {

    private final StorePartnerService storePartnerService;

    @Transactional(readOnly = true)
    public GetPartnerStoreResponse execute(@NonNull String storeId) {
        final Store store = storePartnerService.getStoreById(storeId);

        return new GetPartnerStoreResponse(store);
    }


    public static class GetPartnerStoreResponse extends CommonResponse<PartnerStoreDto> {

        public GetPartnerStoreResponse(Store store) {
            super(new PartnerStoreDto(store));
        }
    }

}
