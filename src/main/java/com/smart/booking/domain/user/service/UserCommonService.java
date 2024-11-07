package com.smart.booking.domain.user.service;

import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.user.entity.User;
import java.util.Optional;
import lombok.NonNull;

public interface UserCommonService {

    @NonNull User getUserById(@NonNull String userId) throws CommonException;

    @NonNull Optional<User> getUserByMember(@NonNull Member member);

}
