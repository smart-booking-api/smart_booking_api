package com.smart.booking.facade.dto.user;

import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import com.smart.booking.domain.user.value_object.UserPolicyAgreement;
import lombok.NonNull;

public record SignUpUserRequest(
    @NonNull ThirdPartyAccountProvider thirdPartyAccountProvider,
    @NonNull String thirdPartyProviderAccountId,
    @NonNull String nickname,
    @NonNull String email,
    @NonNull String phoneNumber,
    @NonNull UserPolicyAgreement policyAgreement
) {

}
