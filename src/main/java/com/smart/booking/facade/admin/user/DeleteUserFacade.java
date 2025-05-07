package com.smart.booking.facade.admin.user;

import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.domain.user.service.UserAdminService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteUserFacade {

    private final UserAdminService userAdminService;

    private final PartnerService partnerService;

    @Transactional
    public DeleteUserResponse execute(
        @NonNull String userId,
        @NonNull MemberContextDto memberContextDto
    ) {

        final var partnerType = this.partnerService.getPartnerTypeByMemberId(memberContextDto.getMemberId());

        if (partnerType != PartnerType.M) {
            throw new CommonException(ResponseCode.NOT_PERMITTED_PARTNER_TYPE);
        }

        this.userAdminService.deleteUser(userId);

        return new DeleteUserResponse(true);
    }


    public static class DeleteUserResponse extends CommonResponse<Boolean> {

        public DeleteUserResponse(@NonNull Boolean result) {
            super(result);
        }
    }
}
