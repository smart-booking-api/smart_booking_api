package com.smart.booking.domain.user.service;

import static com.smart.booking.common.enums.ResponseCode.NOT_FOUND_THIRD_PARTY_ACCOUNT;

import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.repository.UserRepository;
import com.smart.booking.domain.user.value_object.UserPolicyAgreement;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.stereotype.Service;


@Service
class UserUserServiceImpl extends UserCommonServiceImpl implements UserUserService {

    private final UserRepository userRepository;

    public UserUserServiceImpl(@NonNull UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public @NonNull User getUserByThirdPartyAccountId(@NonNull String thirdPartyAccountId) throws CommonException {
        return userRepository.findByThirdPartyAccount_Id(thirdPartyAccountId)
            .orElseThrow(() -> new CommonException(NOT_FOUND_THIRD_PARTY_ACCOUNT));
    }

    @Override
    public void selectMyStore(@NonNull String id, @NonNull Store store) throws CommonException {
        final User user = getUserById(id);
        user.changeMyStore(store);
        userRepository.save(user);
    }

    @Override
    public void withdrawUser(@NonNull String id) throws CommonException {
        final User user = getUserById(id);
        user.withdraw();
        userRepository.save(user);
    }

    @Override
    public @NonNull UserPolicyAgreement getUserPolicyAgreement(@NonNull String userId) throws CommonException {
        return getUserById(userId).getPolicyAgreement();
    }

    @Override
    public @NonNull UserPolicyAgreement updateUserPolicyAgreement(
        @NonNull String userId,
        @NonNull UserPolicyAgreement userPolicyAgreement
    ) throws CommonException {
        final User user = getUserById(userId);
        user.changePolicyAgreement(userPolicyAgreement);

        return userRepository.save(user).getPolicyAgreement();
    }

    @Override
    public @NonNull Optional<User> getUserByEmailId(@NonNull String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void login(@NonNull String userId) throws CommonException {
//        final User user = getUserById(userId);
//        user.login();
//        userRepository.save(user);
    }

    @Override
    public Optional<User> getByProviderUserId(String providerUserId) {
        return userRepository.findByProviderUserId(providerUserId);
    }

}
