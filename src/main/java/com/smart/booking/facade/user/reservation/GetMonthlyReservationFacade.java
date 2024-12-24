package com.smart.booking.facade.user.reservation;

import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.facade.dto.reservation.MonthlyReservationDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMonthlyReservationFacade {
    private final UserReservationService userReservationService;

    public List<MonthlyReservationDto> execute(String year, String month, String memberId) {
        return userReservationService.getMonthlyMyReservationHistory(memberId, year, month).stream()
            .map(MonthlyReservationDto::new)
            .collect(Collectors.toList());
    }
}
