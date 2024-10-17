package com.smart.booking.domain.user.service;

import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public @NonNull User getUserById(@NonNull String userId) {
        return null;
    }

    @Override
    public @NonNull User getUserByThirdPartyAccountId(@NonNull String thirdPartyAccountId) {
        return null;
    }

    @Override
    public void selectMyStore(@NonNull String userId, @NonNull String storeId) {

    }

    @Override
    public void withdrawUser(@NonNull String userId) {

    }

    @Override
    public @NonNull User editUserProfile() {
        return null;
    }

    @Override
    public boolean getPromotionEventAgreementDto(@NonNull String userId) {
        return false;
    }

    @Override
    public boolean updateSmsAgreement(@NonNull String userId, boolean isAgree) {
        return false;
    }
}
