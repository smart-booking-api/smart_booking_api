package com.smart.booking.domain.member.service;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.value_object.CreateMemberDto;
import lombok.NonNull;

public interface MemberService {
    @NonNull Member createMember(@NonNull CreateMemberDto createMemberDto);
    Member getMember(@NonNull String memberId);
}
