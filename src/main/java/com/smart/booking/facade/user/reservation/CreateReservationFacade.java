package com.smart.booking.facade.user.reservation;

import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.domain.user.service.UserService;
import com.smart.booking.facade.dto.payment.CompletePaymentRequestDto;
import com.smart.booking.facade.event.dto.CompletePaymentEventDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateReservationFacade {

    private final UserReservationService userReservationService;

    public void createReservation(@NonNull CompletePaymentEventDto completePaymentEventDto) {

        // 예약
//        userReservationService.createReservation();
        // firebase 예약완료 전송
    }

    private CreateReservationDto getCreateReservationDto(CompletePaymentRequestDto completeDto) {
        return CreateReservationDto.builder()
//            .storeId(completePaymentRequestDto.storeId)
            .teeBoxId(completeDto.teeBoxId())
            .startTimeTableId(completeDto.startTimeTableId())
            .endTimeTableId(completeDto.endTimeTableId())
            .memberId(completeDto.memberId())
            .reservationUserName(completeDto.reservationUserName())
            .reservationUserPhoneNum(completeDto.reservationUserPhoneNum())
            .build();
    }
}
