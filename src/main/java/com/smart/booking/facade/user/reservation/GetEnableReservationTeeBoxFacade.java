package com.smart.booking.facade.user.reservation;

import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.service.StoreUserService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetEnableReservationTeeBoxFacade {

    private final UserReservationService userReservationService;
    private final StoreUserService storeUserService;
//    private final TeeBoxService teeBoxService;

    public List<String> getEnableReservationTeeBox(String storeId, String reservationDate) {
        Store store = storeUserService.getStoreById(storeId);
        List<Reservation> reservations = userReservationService.getReservationByStoreAndReservationDate(store, LocalDate.parse(reservationDate));
        List<String> TeeBoxIds = reservations.stream()
            .map(item -> item.getBox().getId())
            .toList();
//        teeBoxService.getTeeBoxNotIn(store, TeeBoxIds);
        return null;
    }
}
