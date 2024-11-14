package com.smart.booking.facade.user.auth;

import com.smart.booking.domain.user.service.UserUserService;
import com.smart.booking.domain.user.value_object.UserPolicyAgreement;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateUserPolicyAgreementFacade {


    private final UserUserService userUserService;

    public void execute(
        @NonNull String userId,
        @NonNull UserPolicyAgreement userPolicyAgreement
    ) {
        userUserService.updateUserPolicyAgreement(userId, userPolicyAgreement);
    }
}
