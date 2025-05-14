package com.smart.booking.facade.user.user;

import com.smart.booking.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserAgreementFacade {

    private final UserService userService;

    public void execute() {
    }


    public static class UpdateUserAgreementRequest {

        private String userId;


    }

}
