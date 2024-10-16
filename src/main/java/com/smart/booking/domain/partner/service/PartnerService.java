package com.smart.booking.domain.partner.service;

import com.smart.booking.domain.partner.entity.Partner;
import lombok.NonNull;

public interface PartnerService {

    @NonNull Partner createPartner();
}
