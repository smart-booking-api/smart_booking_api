package com.smart.booking.common.dto;

import com.smart.booking.domain.member.enums.MemberType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberContext {

    private String memberId;
    private MemberType memberType;
}
