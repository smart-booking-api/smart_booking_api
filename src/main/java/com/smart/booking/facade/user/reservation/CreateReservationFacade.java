package com.smart.booking.facade.user.reservation;

import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.reservation.dto.CreateReservationDto;
import com.smart.booking.domain.reservation.enums.ReservationStatus;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.service.StoreUserService;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.facade.dto.payment.CompletePaymentRequestDto;
import com.smart.booking.facade.event.dto.CompletePaymentEventDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateReservationFacade {
    private final UserReservationService userReservationService;
    private final StoreUserService storeUserService;
    private final MemberService memberService;

    public void createReservation(@NonNull CompletePaymentEventDto eventDto) {
        // 예약
        Store store = storeUserService.getStoreById(eventDto.storeId());
        Member member = memberService.getMemberById(eventDto.memberId());
        userReservationService.createReservation(getCreateReservationDto(store, null, member, eventDto));

        // firebase 예약완료 전송
    }

    private CreateReservationDto getCreateReservationDto(Store store, TeeBox teeBox, Member member, CompletePaymentEventDto eventDto) {
        return CreateReservationDto.builder()
            .store(store)
            .teeBox(teeBox)
            .startTimeTableId(eventDto.startTimeTableId())
            .endTimeTableId(eventDto.endTimeTableId())
            .member(member)
            .reservationUserName(eventDto.reservationUserName())
            .reservationUserPhoneNumber(eventDto.reservationUserPhoneNum())
            .trackingId(eventDto.trackingId())
            .build();
    }
}
