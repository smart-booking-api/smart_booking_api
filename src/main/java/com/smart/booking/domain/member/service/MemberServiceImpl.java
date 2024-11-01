package com.smart.booking.domain.member.service;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.repository.MemberRepository;
import com.smart.booking.domain.member.value_object.CreateMemberDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public @NonNull Member createMember(@NonNull CreateMemberDto createMemberDto) {
        return null;
    }

    @Override
    public Member getMember(@NonNull String memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }
}
