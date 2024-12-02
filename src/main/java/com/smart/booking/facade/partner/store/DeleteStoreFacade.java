package com.smart.booking.facade.partner.store;

import com.smart.booking.domain.store.service.StorePartnerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class DeleteStoreFacade {

    private final StorePartnerService storePartnerService;

    @Transactional
    public void deleteStore(@NonNull String storeId) {
        storePartnerService.deleteStore(storeId);
    }
}
