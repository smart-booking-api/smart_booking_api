package com.smart.booking.domain.user.mapper;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.user.dto.SignUpUserDto;
import com.smart.booking.domain.user.entity.ThirdPartyAccount;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.entity.UserProfile;
import lombok.NonNull;

public interface UserMapper {

    static @NonNull User toEntity(@NonNull SignUpUserDto signUpUserDto) {

        return User.builder()
            .thirdPartyAccount(
                ThirdPartyAccount.builder()
                    .provider(signUpUserDto.thirdPartyAccountProvider())
                    .providerAccountId(signUpUserDto.thirdPartyProviderAccountId())
                    .build()
            )
            .member(
                Member.builder()
                    .type(MemberType.USER)
                    .build()
            )
            .profile(
                UserProfile.builder()
                    .nickname(signUpUserDto.nickname())
                    .build()
            )
            .email(signUpUserDto.email())
            .phoneNumber(signUpUserDto.phoneNumber())
            .policyAgreement(signUpUserDto.policyAgreement())
            .build();
    }
}
