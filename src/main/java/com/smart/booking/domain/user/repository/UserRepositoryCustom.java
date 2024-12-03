package com.smart.booking.domain.user.repository;

import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import com.smart.booking.domain.user.enums.UserStatus;
import java.util.Optional;
import lombok.NonNull;

public interface UserRepositoryCustom {

    @NonNull
    CursorResult<User> findByNicknameAndStatusWithCursor(
        String nickname,
        UserStatus status,
        String cursor,
        int pageSize
    );

    Optional<User> findByProviderUserIdAndProvider(String providerUserId, ThirdPartyAccountProvider provider);
}
