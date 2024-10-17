package com.smart.booking.domain.member.value_object;

import com.smart.booking.domain.member.enums.MemberType;
import lombok.NonNull;

public record CreateMemberDto(
    @NonNull MemberType memberType,
    @NonNull String relatedId
    ) {

}
