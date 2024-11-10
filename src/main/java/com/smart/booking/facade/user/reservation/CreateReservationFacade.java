package com.smart.booking.facade.user.reservation;

import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.reservation.dto.CreateReservationDto;
import com.smart.booking.domain.reservation.enums.ReservationStatus;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.facade.dto.payment.CompletePaymentRequestDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateReservationFacade {
    private final UserReservationService userReservationService;
    private final StoreUserService storeUserService;
    private final MemberService memberService;

    public void createReservation(@NonNull CompletePaymentRequestDto createReservationDto) {
        // 예약
        Store store = storeUserService.getStoreById(completePaymentEvent.storeId());
        Member member = memberService.getMemberById(completePaymentEvent.memberId());
        userReservationService.createReservation(getCreateReservationDto(store, null, member, completePaymentEvent));

        // firebase 예약완료 전송
    }

    private CreateReservationDto getCreateReservationDto(Store store, TeeBox teeBox, Member member, TempCompletePaymentEvent completePaymentEvent) {
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
