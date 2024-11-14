package com.smart.booking.facade.user.auth;

import com.smart.booking.domain.auth.value_object.Token;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import com.smart.booking.domain.user.service.UserUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class SingInUserFacade {

    private final UserUserService userUserService;

    @Transactional
    public Token execute(
        @NonNull ThirdPartyAccountProvider provider,
        @NonNull String thirdPartyAccountProviderId
    ) {
        final User user = userUserService.login(provider, thirdPartyAccountProviderId);

        user.getMember();

        return new Token(
            "accessToken",
            "refreshToken"
        );
    }
}
