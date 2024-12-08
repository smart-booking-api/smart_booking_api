package com.smart.booking.presentation.security.utils;

import com.smart.booking.common.dto.MemberContextDto;
import lombok.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static @NonNull MemberContextDto getCurrentMemberContext() {
        return (MemberContextDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
