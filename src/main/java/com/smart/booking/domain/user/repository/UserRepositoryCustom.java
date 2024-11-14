package com.smart.booking.domain.user.repository;

import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.enums.UserStatus;
import lombok.NonNull;

public interface UserRepositoryCustom {

    @NonNull
    CursorResult<User> findByNicknameAndStatusWithCursor(
        String nickname,
        UserStatus status,
        String cursor,
        int pageSize
    );


    boolean existsByPhoneNumberOrEmail(@NonNull String phoneNumber, @NonNull String email);

}
