package com.smart.booking.domain.user.service;

import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.user.dto.CreateUserDto;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import com.smart.booking.domain.user.value_object.UserPolicyAgreement;
import java.util.Optional;
import lombok.NonNull;

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

    Optional<User> getByProviderUserIdAndProvider(@NonNull String providerUserId, @NonNull ThirdPartyAccountProvider provider);

    @NonNull
    User createUser(@NonNull CreateUserDto createUserDto);

}
