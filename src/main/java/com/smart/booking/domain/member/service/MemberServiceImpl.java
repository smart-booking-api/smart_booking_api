package com.smart.booking.domain.member.service;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.member.repository.MemberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public @NonNull Member createMember(@NonNull MemberType memberType) {

        return memberRepository.save(
            Member.builder()
                .type(memberType)
                .build()
        );
    }

    @Override
    public Member getMemberById(@NonNull String memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    public @NonNull Member getMemberByIdOrThrow(@NonNull String memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }
}
