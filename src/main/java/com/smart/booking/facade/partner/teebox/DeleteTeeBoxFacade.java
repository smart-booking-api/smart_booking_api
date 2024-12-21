package com.smart.booking.facade.partner.teebox;

import com.smart.booking.common.dto.CommonEmptyResponse;
import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.domain.tee_box.service.TeeBoxPartnerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteTeeBoxFacade {

    private final PartnerService partnerService;
    private final TeeBoxPartnerService teeBoxPartnerService;

    @Transactional
    public DeleteTeeBoxResponse execute(
        @NonNull String storeId,
        @NonNull String teeBoxId,
        @NonNull MemberContextDto memberContextDto
    ) {

        final PartnerType partnerType = partnerService.getPartnerTypeByMemberId(memberContextDto.getMemberId());

        if (partnerType != PartnerType.M) {
            throw new CommonException(ResponseCode.NOT_PERMITTED_PARTNER_TYPE);
        }

        // TODO: 진족 - 해당 시점 이후 타석 예약 정보 확인 해서 삭제 가능 여부 확인 필요

        teeBoxPartnerService.deleteTeeBox(teeBoxId);

        return new DeleteTeeBoxResponse();
    }


    public static class DeleteTeeBoxResponse extends CommonEmptyResponse {

    }
}
