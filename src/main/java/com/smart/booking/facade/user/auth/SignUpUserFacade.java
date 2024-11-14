package com.smart.booking.facade.user.auth;

import com.smart.booking.domain.auth.value_object.Token;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.member.value_object.CreateMemberDto;
import com.smart.booking.domain.user.dto.SignUpUserDto;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.service.UserUserService;
import com.smart.booking.facade.dto.user.SignUpUserRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class SignUpUserFacade {

    //    private final JwtService jwtService;
    private final MemberService memberService;
    private final UserUserService userUserService;

    @Transactional
    public @NonNull Token execute(@NonNull SignUpUserRequest signUpUserRequest) {
        final Member member = memberService.createMember(new CreateMemberDto(MemberType.USER));
        final User user = userUserService.signUp(
            new SignUpUserDto(
                signUpUserRequest.thirdPartyAccountProvider(),
                signUpUserRequest.thirdPartyProviderAccountId(),
                member,
                signUpUserRequest.nickname(),
                signUpUserRequest.email(),
                signUpUserRequest.phoneNumber(),
                signUpUserRequest.policyAgreement()
            )
        );

        /// TODO: 토큰 발급 로직 추가 필요
        return new Token(
            "accessToken",
            "refreshToken"
        );


    }
}
