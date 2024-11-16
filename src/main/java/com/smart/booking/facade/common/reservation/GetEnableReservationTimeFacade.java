package com.smart.booking.facade.common.reservation;

import com.smart.booking.domain.common.entity.CommonTimeTable;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.service.CommonReservationService;
import com.smart.booking.domain.reservation.service.CommonReservationServiceImpl;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.entity.StoreOperationInfo;
import com.smart.booking.domain.store.service.StoreUserService;
import com.smart.booking.domain.common.model.TimeCode;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.domain.tee_box.service.TeeBoxService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class GetEnableReservationTimeFacade {
    private final StoreUserService storeUserService;
    private final CommonReservationService commonReservationService;

    public GetEnableReservationTimeFacade(StoreUserService storeUserService, UserReservationService userReservationService) {
        this.storeUserService = storeUserService;
        this.commonReservationService = userReservationService;
    }

    //    private final TeeBoxService teeBoxService;
    public List<TimeCode> execute(@RequestParam String storeId, @RequestParam String teeBoxId) {
        Store store = storeUserService.getStoreById(storeId);
//        TeeBox teeBox = teeBoxService.getTeeBoxById(teeBoxId);
//        StoreOperationInfo operationInfo = store.getOperationInfo();
//        List<CommonTimeTable> commonTimeTable = commonTimeService.getTimeBetween(operationInfo.getOpenTime(), operationInfo.getCloseTime());
//        List<Reservation> reservations = userReservationService.getStoreReservationAndTeebox(store, teeBox);

        // reservations에서 예약된 시간 목록을 추출
//        Set<Long> reservedTimes = reservations.stream()
//            .map(reservation -> reservation.getStartTimeId())
//            .collect(Collectors.toSet());

//        List<CommonTimeTable> enableTimes = commonTimeTable.stream()
//            .filter(common -> !reservedTimes.contains(common.getTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))))
//            .collect(Collectors.toList());
        return null;
    }
}
