package com.smart.booking.domain.user.service;


import static com.smart.booking.common.enums.ResponseCode.NOT_FOUND_USER;

import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
abstract class UserCommonServiceImpl implements UserCommonService {

    private final UserRepository userRepository;

    @Override
    public @NonNull User getUserById(@NonNull String userId) throws CommonException {
        return userRepository.findById(userId)
            .orElseThrow(() -> new CommonException(NOT_FOUND_USER));
    }

    @Override
    public @NonNull User getUserByMember(@NonNull Member member) {
        return userRepository.findByMember(member)
            .orElseThrow(() -> new CommonException(NOT_FOUND_USER));
    }


}
