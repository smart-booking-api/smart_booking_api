package com.smart.booking.facade.partner.teebox;

import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.domain.tee_box.service.TeeBoxPartnerService;
import com.smart.booking.facade.dto.teebox.TeeBoxDto;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetPartnerTeeBoxesFacade {

    private final TeeBoxPartnerService teeBoxPartnerService;


    @Transactional(readOnly = true)
    public GetPartnerTeeBoxesResponse execute(@NonNull String storeId) {
        final List<TeeBox> teeBoxes = teeBoxPartnerService.getTeeBoxesByStoreId(storeId);

        return new GetPartnerTeeBoxesResponse(teeBoxes.stream().map(TeeBoxDto::new).toList());
    }

    public static class GetPartnerTeeBoxesResponse extends CommonResponse<@NonNull List<TeeBoxDto>> {

        public GetPartnerTeeBoxesResponse(@NonNull List<TeeBoxDto> data) {
            super(data);
        }

    }

}
