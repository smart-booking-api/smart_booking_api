package com.smart.booking.facade.user.auth;

import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.domain.auth.service.AuthService;
import com.smart.booking.domain.auth.value_object.Token;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.user.dto.CreateUserDto;
import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import com.smart.booking.domain.user.service.UserService;
import com.smart.booking.domain.user.value_object.UserPolicyAgreement;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthFacade {

    private final AuthService authService;
    private final MemberService memberService;
    private final UserService userService;

    @Transactional
    public SignUpUserResponse signUp(@NonNull SignUpUserRequest request) {

        final var member = this.memberService.createMember(MemberType.USER);
        final var user = this.userService.createUser(request.toCreateUserDto(member));

        final var token = this.authService.generateToken(
            user.getMember().getId(),
            user.getMember().getType()
        );

        return new SignUpUserResponse(token);
    }

    @Transactional
    public SignInUserResponse signIn(@NonNull SignInUserRequest request) {
        final var user = this.userService.login(request.provider, request.providerUserId);

        final var token = this.authService.generateToken(user.getMember().getId(), user.getMember().getType());

        return new SignInUserResponse(token);
    }

    @RequiredArgsConstructor
    public static class SignUpUserRequest {

        @NotNull
        @Schema(description = "서드파티 로그인 제공사")
        public final ThirdPartyAccountProvider provider;

        @NotNull
        @Schema(description = "서드파티 로그인 제공사 아이디")
        public final String providerUserId;

        @NotNull
        @Schema(description = "닉네임")
        public final String nickname;

        @NotNull
        @Schema(description = "전화번호")
        public final String phone;

        @NotNull
        @Schema(description = "이메일")
        public final String email;

        @NotNull
        @Schema(description = "푸시토큰")
        public final String pushToken;

        @NotNull
        @Schema(description = "정책 동의 여부")
        public final UserPolicyAgreement policyAgreement;

        public CreateUserDto toCreateUserDto(@NonNull Member member) {
            return new CreateUserDto(
                member,
                this.provider,
                this.providerUserId,
                this.nickname,
                this.phone,
                this.email,
                this.pushToken,
                this.policyAgreement
            );
        }
    }


    public static class SignUpUserResponse extends CommonResponse<Token> {

        public SignUpUserResponse(@NonNull Token token) {
            super(token);
        }

    }


    @RequiredArgsConstructor
    public static class SignInUserRequest {

        @NotBlank(message = "로그인 ID를 입력해주세요.")
        @Schema(description = "로그인 ID")
        private String providerUserId;

        @NotBlank(message = "제공자를 입력해주세요")
        @Schema(description = "provider")
        private ThirdPartyAccountProvider provider;
    }

    public static class SignInUserResponse extends CommonResponse<Token> {

        public SignInUserResponse(@NonNull Token token) {
            super(token);
        }

    }

}
