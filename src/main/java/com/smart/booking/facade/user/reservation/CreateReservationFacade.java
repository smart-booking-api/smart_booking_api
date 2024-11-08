package com.smart.booking.facade.user.reservation;

import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.facade.dto.payment.CompletePaymentRequestDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateReservationFacade {

    private final UserReservationService userReservationService;

    public void createReservation(@NonNull CompletePaymentRequestDto createReservationDto) {
        // 예약
        userReservationService.createReservation(getCreateReservationDto(createReservationDto));

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
