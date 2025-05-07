package com.smart.booking.domain.user.dto;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import com.smart.booking.domain.user.value_object.UserPolicyAgreement;
import lombok.NonNull;

public record CreateUserDto(
    @NonNull Member member,
    @NonNull ThirdPartyAccountProvider provider,

    @NonNull String providerUserId,

    @NonNull String nickname,

    @NonNull String phone,

    @NonNull String email,

    @NonNull String pushToken,

    @NonNull UserPolicyAgreement policyAgreement
) {

}
