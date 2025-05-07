package com.smart.booking.domain.user.service;

import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.user.dto.GetUsersDto;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.repository.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
class UserAdminServiceImpl extends UserCommonServiceImpl implements UserAdminService {

    final private UserRepository userRepository;

    public UserAdminServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public @NonNull CursorResult<User> getUsers(@NonNull GetUsersDto getUsersDto) {
        return userRepository.findByNicknameAndStatusWithCursor(
            getUsersDto.nickname(),
            getUsersDto.status(),
            getUsersDto.cursor(),
            getUsersDto.pageSize()
        );
    }

    @Override
    public void deleteUser(@NonNull String userId) {
        userRepository.deleteById(userId);
    }


}
