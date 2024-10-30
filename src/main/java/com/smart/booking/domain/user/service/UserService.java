package com.smart.booking.domain.user.service;

import com.smart.booking.domain.user.entity.User;
import lombok.NonNull;

public interface UserService {

    @NonNull User getUserById(@NonNull String userId);

    @NonNull User getUserByThirdPartyAccountId(@NonNull String thirdPartyAccountId);

    void selectMyStore(@NonNull String userId, @NonNull String storeId);

    void withdrawUser(@NonNull String userId);

    @NonNull User editUserProfile();

    boolean getPromotionEventAgreementDto(@NonNull String userId);

    boolean updateSmsAgreement(@NonNull String userId, boolean isAgree);


}
