package com.smart.booking.domain.user.service;


import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.user.dto.SignUpUserDto;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import com.smart.booking.domain.user.mapper.UserMapper;
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
    public @NonNull User login(
        @NonNull ThirdPartyAccountProvider provider,
        @NonNull String thirdPartyAccountId
    ) {
        final User user = userRepository.findByThirdPartyAccount_ProviderAndThirdPartyAccount_ProviderAccountId(
            provider,
            thirdPartyAccountId
        ).orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_THIRD_PARTY_ACCOUNT));

        user.login();

        return user;
    }

    @Override
    public void selectMyStore(@NonNull String id, @NonNull Store store) {
        final User user = getUserById(id);
        user.changeMyStore(store);
        userRepository.save(user);
    }

    @Override
    public void withdrawUser(@NonNull String id) {
        final User user = getUserById(id);
        user.withdraw();
        userRepository.save(user);
    }


    @Override
    public @NonNull UserPolicyAgreement updateUserPolicyAgreement(
        @NonNull String userId,
        @NonNull UserPolicyAgreement userPolicyAgreement
    ) {
        final User user = getUserById(userId);
        user.changePolicyAgreement(userPolicyAgreement);

        return userRepository.save(user).getPolicyAgreement();
    }

    @Override
    public @NonNull Optional<User> getUserByEmailId(@NonNull String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User signUp(@NonNull SignUpUserDto signUpUserDto) {
        if (userRepository.existsByEmail(signUpUserDto.email())) {
            throw new CommonException(ResponseCode.DUPLICATED_USER_EMAIL);
        }

        if (userRepository.existsByPhoneNumber(signUpUserDto.phoneNumber())) {
            throw new CommonException(ResponseCode.DUPLICATED_USER_PHONE_NUMBER);
        }

        return userRepository.save(UserMapper.toEntity(signUpUserDto));
    }

}
