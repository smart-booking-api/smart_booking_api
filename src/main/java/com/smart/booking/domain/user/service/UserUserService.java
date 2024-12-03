package com.smart.booking.domain.user.service;

import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import com.smart.booking.domain.user.value_object.UserPolicyAgreement;
import java.util.Optional;
import lombok.NonNull;

public interface UserUserService extends UserCommonService {

    @NonNull User getUserByThirdPartyAccountId(@NonNull String thirdPartyAccountId) throws CommonException;

    void selectMyStore(@NonNull String id, @NonNull Store store) throws CommonException;

    void withdrawUser(@NonNull String id) throws CommonException;

    @NonNull UserPolicyAgreement getUserPolicyAgreement(@NonNull String userId) throws CommonException;

    @NonNull UserPolicyAgreement updateUserPolicyAgreement(@NonNull String userId, @NonNull UserPolicyAgreement userPolicyAgreement)
        throws CommonException;

    @NonNull Optional<User> getUserByEmailId(@NonNull String email);

    void login(@NonNull String userId) throws CommonException;

    Optional<User> getByProviderUserIdAndProvider(String providerUserId, ThirdPartyAccountProvider provider);
}
