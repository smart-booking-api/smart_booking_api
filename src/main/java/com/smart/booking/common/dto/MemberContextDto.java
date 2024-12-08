package com.smart.booking.common.dto;

import com.smart.booking.domain.member.enums.MemberType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberContextDto {

    private String memberId;
    private MemberType memberType;
}
