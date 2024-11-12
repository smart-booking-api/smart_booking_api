package com.smart.booking.facade.user.reservation;

import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.service.StoreUserService;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetEnableReservationTeeBoxFacade {

    private final UserReservationService userReservationService;
    private final StoreUserService storeUserService;
//    private final TeeBoxService teeBoxService;

    public List<String> getEnableReservationTeeBox(String storeId, String reservationDate) throws CommonException {
        Store store = storeUserService.getStoreById(storeId);
        List<TeeBox> teeBox = new ArrayList<>();
        //todo
//      List<TeeBox> teeBox = teeBoxService.getTeeBoxByStore(store);
        List<Reservation> reservations = userReservationService.getStoreReservation(store, LocalDate.parse(reservationDate));
        List<String> TeeBoxIds = reservations.stream()
            .filter(reservation -> !teeBox.contains(reservation.getBox().getId()))
            .map(item-> item.getBox().getId())
            .collect(Collectors.toList());

        return TeeBoxIds;
    }
}
