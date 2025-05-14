package com.smart.booking.facade.user.reservation;

import com.smart.booking.domain.payment.dto.PaymentResponseDto;
import com.smart.booking.domain.payment.service.PaymentHistoryService;
import com.smart.booking.domain.payment.service.PaymentService;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.service.ReservationTimeService;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.facade.dto.reservation.CardReceiptDto;
import com.smart.booking.facade.dto.reservation.CardReceiptDto.AmountInfo;
import com.smart.booking.facade.dto.reservation.CardReceiptDto.PaymentInfo;
import com.smart.booking.facade.dto.reservation.CardReceiptDto.SellerInfo;
import com.smart.booking.facade.dto.reservation.CardReceiptDto.StoreInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetCardReceiptFacade {
    private final UserReservationService userReservationService;
    private final PaymentService paymentService;
    private final ReservationTimeService reservationTimeService;

    public CardReceiptDto execute(String reservationId) {
        Reservation reservation = userReservationService.getReservationById(reservationId);
        String startTime = reservationTimeService.getReservationTimeCodeById(reservation.getStartTimeId()).getTimeName();
        String endTime = reservationTimeService.getReservationTimeCodeById(reservation.getEndTimeId()).getTimeName();
        PaymentResponseDto paymentResponseDto = paymentService.getPaymentResponse(reservation.getPaymentId());

        return CardReceiptDto.builder()
            .reservationInfo(new CardReceiptDto.ReservationInfo(reservation, startTime, endTime))
            .amountInfo(new AmountInfo(paymentResponseDto))
            .paymentInfo(new PaymentInfo(paymentResponseDto))
            .sellerInfo(new SellerInfo(paymentResponseDto))
            .storeInfo(new StoreInfo(paymentResponseDto))
            .build();
    }
}
