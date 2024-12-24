package com.smart.booking.facade.partner.reservation;

import com.smart.booking.domain.reservation.service.PartnerReservationService;
import com.smart.booking.facade.dto.reservation.GetPhoneReservationDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class GetPhoneReservationFacade {
    private final PartnerReservationService partnerReservationService;

    @Transactional
    public List<GetPhoneReservationDto> execute(String searchText, String memberId) {
        return partnerReservationService.getPhoneReservationList(searchText, memberId).stream()
            .map(GetPhoneReservationDto::new)
            .collect(Collectors.toList());
    }
}
