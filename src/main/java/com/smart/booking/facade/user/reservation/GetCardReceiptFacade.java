package com.smart.booking.facade.user.reservation;

import com.smart.booking.domain.payment.service.PaymentHistoryService;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.service.ReservationTimeService;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.facade.dto.reservation.CardReceiptDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetCardReceiptFacade {
    private final UserReservationService userReservationService;
    private final PaymentHistoryService paymentHistoryService;
    private final ReservationTimeService reservationTimeService;

    public CardReceiptDto execute(String reservationId) {
        Reservation reservation = userReservationService.getReservationById(reservationId);
        String startTime = reservationTimeService.getReservationTimeCodeById(reservation.getStartTimeId()).getTimeName();
        String endTime = reservationTimeService.getReservationTimeCodeById(reservation.getEndTimeId()).getTimeName();

        return CardReceiptDto.builder()
            .reservationInfo(new CardReceiptDto.ReservationInfo(reservation, startTime, endTime))
              // todo api 로 가져와야하는지?
//            .amountInfo()
//            .paymentInfo()
//            .sellerInfo()
//            .storeInfo()
            .build();
    }
}
