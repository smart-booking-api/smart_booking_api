package com.smart.booking.domain.user.service;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.user.entity.User;
import lombok.NonNull;

public interface UserCommonService {

    @NonNull
    User getUserById(@NonNull String userId);

    @NonNull
    User getUserByMember(@NonNull Member member);

}
