package com.smart.booking.facade.user.reservation;

import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.enums.ReservationStatus;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.facade.dto.reservation.MonthlyReservation;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMonthlyReservationFacade {
    private final UserReservationService userReservationService;

    public Map<ReservationStatus, List<MonthlyReservation>> execute(String year, String month, String memberId) {
        List<Reservation> reservations = userReservationService.getMonthlyMyReservationHistory(memberId, year, month);

        return reservations.stream()
            .map(MonthlyReservation::new)
            .collect(Collectors.groupingBy(MonthlyReservation::getReservationStatus));
    }
}
