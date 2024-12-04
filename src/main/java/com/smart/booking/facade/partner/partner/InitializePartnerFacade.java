package com.smart.booking.facade.partner.partner;

import com.smart.booking.domain.partner.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InitializePartnerFacade {

    private final PartnerService partnerService;


}
