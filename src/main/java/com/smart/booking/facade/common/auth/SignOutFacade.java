package com.smart.booking.facade.common.auth;

import com.smart.booking.common.dto.CommonEmptyResponse;
import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.domain.auth.service.AuthService;
import com.smart.booking.domain.member.service.MemberService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignOutFacade {

    private final AuthService authService;
    private final MemberService memberService;


    public SignOutResponse execute(@NonNull MemberContextDto memberContextDto) {

        final var member = memberService.getMemberByIdOrThrow(memberContextDto.getMemberId());

        authService.deleteRefreshTokenByMember(member);

        /// TODO: PUSH TOKEN 제거 필요

        return new SignOutResponse();
    }

    public static class SignOutResponse extends CommonEmptyResponse {

    }

}
