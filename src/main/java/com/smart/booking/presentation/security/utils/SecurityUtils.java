package com.smart.booking.presentation.security.utils;

import com.smart.booking.common.dto.MemberContext;
import lombok.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static @NonNull MemberContext getCurrentMemberContext() {
        return (MemberContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
