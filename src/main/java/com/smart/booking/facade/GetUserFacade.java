package com.smart.booking.facade;

import com.smart.booking.domain.common.facade.Facade;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GetUserFacade implements Facade<String, User> {

    private UserService userService;

    @Override
    public @NonNull User execute(String dto) {
//        final User user = userService.getUserById(dto);
        return userService.getUserById(dto);
//        return new GetUserFacadeResponse(user);
    }
}
