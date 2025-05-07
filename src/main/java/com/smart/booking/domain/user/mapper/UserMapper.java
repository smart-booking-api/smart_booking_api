package com.smart.booking.domain.user.mapper;

import com.smart.booking.common.cipher.SecureString;
import com.smart.booking.domain.user.dto.CreateUserDto;
import com.smart.booking.domain.user.entity.ThirdPartyAccount;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.entity.UserProfile;
import com.smart.booking.domain.user.enums.UserStatus;
import lombok.NonNull;

public interface UserMapper {

    static @NonNull User toUser(@NonNull CreateUserDto dto) {
        return User.builder().profile(toUserProfile(dto))
            .thirdPartyAccount(toThirdPartyAccount(dto))
            .email(dto.email())
            .phone(SecureString.of(dto.phone()))
            .member(dto.member())
            .status(UserStatus.ACTIVE)
            .policyAgreement(dto.policyAgreement())
            .build();
    }


    private static @NonNull UserProfile toUserProfile(@NonNull CreateUserDto dto) {
        return UserProfile.builder()
            .nickname(dto.nickname())
            .build();
    }

    private static @NonNull ThirdPartyAccount toThirdPartyAccount(@NonNull CreateUserDto dto) {
        return ThirdPartyAccount.builder()
            .provider(dto.provider())
            .providerUserId(dto.providerUserId())
            .build();
    }
}
