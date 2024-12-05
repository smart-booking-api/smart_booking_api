package com.smart.booking.domain.user.service;

import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import com.smart.booking.domain.user.value_object.UserPolicyAgreement;
import lombok.NonNull;

import java.util.Optional;

public interface UserUserService extends UserCommonService {

    @NonNull
    User getUserByThirdPartyAccountId(@NonNull String thirdPartyAccountId);

    void selectMyStore(@NonNull String id, @NonNull Store store);

    void withdrawUser(@NonNull String id);

    @NonNull
    UserPolicyAgreement getUserPolicyAgreement(@NonNull String userId);

    @NonNull
    UserPolicyAgreement updateUserPolicyAgreement(@NonNull String userId, @NonNull UserPolicyAgreement userPolicyAgreement);

    @NonNull
    Optional<User> getUserByEmailId(@NonNull String email);

    void login(@NonNull String userId);

    Optional<User> getByProviderUserIdAndProvider(String providerUserId, ThirdPartyAccountProvider provider);

}
