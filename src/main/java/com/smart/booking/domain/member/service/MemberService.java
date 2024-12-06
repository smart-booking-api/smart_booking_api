package com.smart.booking.domain.member.service;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import lombok.NonNull;

public interface MemberService {

    @NonNull Member createMember(@NonNull MemberType memberType);

    Member getMemberById(@NonNull String memberId);

    @NonNull Member getMemberByIdOrThrow(@NonNull String memberId);

    void deleteMember(@NonNull Member member);
}
