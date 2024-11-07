package com.smart.booking.domain.user.service;

import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.user.dto.GetUsersDto;
import com.smart.booking.domain.user.entity.User;
import lombok.NonNull;

public interface UserAdminService extends UserCommonService {

    @NonNull CursorResult<User> getUsers(@NonNull GetUsersDto getUsersDto);
}
