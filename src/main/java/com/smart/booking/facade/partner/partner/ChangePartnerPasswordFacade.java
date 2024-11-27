package com.smart.booking.facade.partner.partner;

import com.smart.booking.common.dto.CommonEmptyResponse;
import com.smart.booking.common.dto.MemberContext;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.partner.dto.ChangePartnerPasswordDto;
import com.smart.booking.domain.partner.service.PartnerService;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ChangePartnerPasswordFacade {

    private final MemberService memberService;
    private final PartnerService partnerService;
    

    @Transactional
    public ChangePartnerPasswordResponse execute(
        @NonNull MemberContext context,
        @NonNull ChangePartnerPasswordRequestDto changePartnerPasswordRequestDto
    ) {
        final Member member = memberService.getMemberByIdOrThrow(context.getMemberId());

        partnerService.changePassword(member, changePartnerPasswordRequestDto.toChangePartnerPasswordDto());

        return new ChangePartnerPasswordResponse();
    }


    @Getter
    @RequiredArgsConstructor
    public static class ChangePartnerPasswordRequestDto {

        @NotNull
        private final String password;
        @NotNull
        private final String newPassword;

        public ChangePartnerPasswordDto toChangePartnerPasswordDto() {
            return new ChangePartnerPasswordDto(password, newPassword);
        }
    }

    public static class ChangePartnerPasswordResponse extends CommonEmptyResponse {

    }
}
