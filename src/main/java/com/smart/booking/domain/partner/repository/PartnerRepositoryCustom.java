package com.smart.booking.domain.partner.repository;

import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.enums.PartnerType;
import lombok.NonNull;

public interface PartnerRepositoryCustom {

    @NonNull
    CursorResult<Partner> findByTypeAndCompanyNameWithCursor(
        PartnerType type,
        String companyName,
        String cursor,
        int pageSize
    );

}
