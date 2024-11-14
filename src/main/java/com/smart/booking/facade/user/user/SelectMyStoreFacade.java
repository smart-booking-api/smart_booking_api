package com.smart.booking.facade.user.user;

import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.service.StoreUserService;
import com.smart.booking.domain.user.service.UserUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class SelectMyStoreFacade {

    private final StoreUserService storeUserService;
    private final UserUserService userUserService;

    @Transactional
    public Store execute(@NonNull String userId, @NonNull String storeId) {
        final Store store = storeUserService.getStoreById(storeId);

        userUserService.selectMyStore(userId, store);

        return store;
    }
}
