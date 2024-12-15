package com.smart.booking.facade.user.reservation;

import com.smart.booking.common.firebase.FirebaseComponent;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.reservation.dto.UpsertReservationDto;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.domain.tee_box.service.TeeBoxCommonService;
import com.smart.booking.facade.dto.reservation.ReservationFirebaseStatus;
import com.smart.booking.facade.event.dto.RefundPaymentEventDto;
import com.smart.booking.facade.event.dto.CompletePaymentEventDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RefundReservationFacade {
    private static final String COLLECTION_NAME = "booking";
    private final UserReservationService userReservationService;
    private final FirebaseComponent firebaseComponent;
    private final MemberService memberService;

    /**
     * 결제취소 처리
     * @param eventDto
     */
    public void execute(@NonNull RefundPaymentEventDto eventDto) throws Exception {
        Member member = memberService.getMemberById(eventDto.memberId());

        // 예약취소
        userReservationService.cancelReservation(eventDto.reservationId(), eventDto.memberId(), member.getType());

        // firebase 처리상태 업데이트
        firebaseComponent.updateDocument(COLLECTION_NAME, eventDto.trackingId(),
            new ReservationFirebaseStatus(eventDto.trackingId(), eventDto.memberId(), PaymentStatus.REFUND));
    }
}
