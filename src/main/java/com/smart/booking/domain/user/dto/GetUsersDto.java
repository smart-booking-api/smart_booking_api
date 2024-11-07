package com.smart.booking.domain.user.dto;

import com.smart.booking.domain.user.enums.UserStatus;

public record GetUsersDto(
    String nickname,
    UserStatus status,
    String cursor,
    int pageSize
) {

}
