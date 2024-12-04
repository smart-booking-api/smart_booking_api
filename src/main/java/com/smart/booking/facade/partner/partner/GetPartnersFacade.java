package com.smart.booking.facade.partner.partner;

import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.partner.service.PartnerService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetPartnersFacade {

    private final PartnerService partnerService;

    public void execute() {
    }


    @Getter
    @RequiredArgsConstructor
    public static class GetPartnersRequestDto {

        private final PartnerType type;
        private final String companyName;
        @Schema(defaultValue = "20")
        private final Integer pageSize;
        private final String cursor;

        public int getPageSize() {
            return pageSize == null ? 20 : pageSize;
        }
    }

    public static class GetPartnersResponse {

    }
}
