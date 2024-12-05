package com.smart.booking.presentation.controller;

import com.smart.booking.facade.user.store.GetUserStoreFacade;
import com.smart.booking.presentation.controller.endPoint.UserStoreEndPoint;
import com.smart.booking.presentation.security.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserStoreController {

    private final GetUserStoreFacade getUserStoreFacade;


    @GetMapping(UserStoreEndPoint.GET_MY_STORE)
    public GetUserStoreFacade.GetUserStoreResponse getStore() {
        return getUserStoreFacade.execute(SecurityUtils.getCurrentMemberContext());
    }

}
