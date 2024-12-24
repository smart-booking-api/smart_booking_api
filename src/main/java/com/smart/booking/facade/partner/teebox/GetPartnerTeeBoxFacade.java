package com.smart.booking.facade.partner.teebox;

import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.domain.tee_box.service.TeeBoxPartnerService;
import com.smart.booking.facade.dto.teebox.TeeBoxDto;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetPartnerTeeBoxFacade {

    private final TeeBoxPartnerService teeBoxPartnerService;


    @Transactional(readOnly = true)
    public GetPartnerTeeBoxResponse execute(@NonNull String storeId, @NonNull String teeBoxId) {
        final TeeBox teeBox = teeBoxPartnerService.getTeeBoxById(teeBoxId);

        return new GetPartnerTeeBoxResponse(new TeeBoxDto(teeBox));
    }

    public static class GetPartnerTeeBoxResponse extends CommonResponse<@NotNull TeeBoxDto> {

        public GetPartnerTeeBoxResponse(@NonNull TeeBoxDto data) {
            super(data);
        }

    }

}
