package com.smart.booking.facade.partner.partner;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.partner.dto.CreatePartnerDto;
import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.facade.dto.partner.PartnerDetailDto;
import com.smart.booking.facade.event.dto.CreatePartnerSuccessEvent;
import com.smart.booking.facade.event.publisher.PartnerEventPublisher;
import jakarta.validation.constraints.NotNull;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CreatePartnerFacade {

    private final MemberService memberService;
    private final PartnerService partnerService;
    private final PartnerEventPublisher partnerEventPublisher;

    @Transactional
    public PartnerDetailDto execute(@NonNull CreatePartnerRequestDto createPartnerRequestDto) {
        final String password = this.generateRandomPassword(6);

        final Member member = memberService.createMember(MemberType.PARTNER);

        final Partner partner = partnerService.createPartner(createPartnerRequestDto.toCreatePartnerDto(member, password));

        final String message = "[스마트부킹 파트너]"
            + " "
            + createPartnerRequestDto.phoneNumber
            + " 님의 임시 비밀번호는 "
            + password
            + " 입니다.";

        partnerEventPublisher.publishCreatePartnerSuccessEvent(new CreatePartnerSuccessEvent(partner.getPhoneNumber(), message));

        return new PartnerDetailDto(partner);
    }

    private String generateRandomPassword(int length) {
        return new Random().ints(length, 0, 10)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining());
    }

    public record CreatePartnerRequestDto(
        @NotNull PartnerType type,
        @NotNull String code,
        @NotNull String phoneNumber

    ) {

        public CreatePartnerDto toCreatePartnerDto(@NonNull Member member, @NonNull String password) {
            return new CreatePartnerDto(
                member,
                type,
                code,
                phoneNumber,
                password
            );
        }

    }


}
