package com.smart.booking.domain.user.dto;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import com.smart.booking.domain.user.value_object.UserPolicyAgreement;
import lombok.NonNull;

public record SignUpUserDto(
    @NonNull ThirdPartyAccountProvider thirdPartyAccountProvider,
    @NonNull String thirdPartyProviderAccountId,
    @NonNull Member member,
    @NonNull String nickname,
    @NonNull String email,
    @NonNull String phoneNumber,
    @NonNull UserPolicyAgreement policyAgreement
) {

}
