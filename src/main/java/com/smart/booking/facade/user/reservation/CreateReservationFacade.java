package com.smart.booking.facade.user.reservation;

import com.smart.booking.common.firebase.FirebaseComponent;
import com.smart.booking.domain.common.enums.TeeBoxType;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.reservation.dto.UpsertReservationDto;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.entity.StoreTeeBoxFee;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.domain.tee_box.service.TeeBoxCommonService;
import com.smart.booking.facade.dto.reservation.ReservationFirebaseStatusDto;
import com.smart.booking.facade.event.dto.CompletePaymentEventDto;
import java.math.BigDecimal;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateReservationFacade {
    private static final String COLLECTION_NAME = "booking";
    private final UserReservationService userReservationService;
    private final TeeBoxCommonService teeBoxService;
    private final MemberService memberService;
    private final FirebaseComponent firebaseComponent;

    /**
     * 결제 완료 후 예약처리
     * @param eventDto
     */
    public void execute(@NonNull CompletePaymentEventDto eventDto) {
        // 예약
        TeeBox teeBox = teeBoxService.getTeeBoxById(eventDto.teeBoxId());
        Member member = memberService.getMemberById(eventDto.memberId());
        Reservation reservation = userReservationService.createReservation(getCreateReservationDto(teeBox.getStore(), teeBox, member, eventDto));

        // firebase 처리상태 업데이트
        firebaseComponent.updateDocument(COLLECTION_NAME, eventDto.trackingId(),
            new ReservationFirebaseStatusDto(eventDto.trackingId(), eventDto.memberId(), PaymentStatus.COMPLETE, reservation.getReservationNo()));
    }

    private UpsertReservationDto getCreateReservationDto(Store store, TeeBox teeBox, Member member, CompletePaymentEventDto eventDto) {
        return UpsertReservationDto.builder()
            .store(store)
            .teeBox(teeBox)
            .startTimeTableId(eventDto.startTimeTableId())
            .endTimeTableId(eventDto.endTimeTableId())
            .member(member)
            .reservationUserName(eventDto.reservationUserName())
            .reservationUserPhoneNumber(eventDto.reservationUserPhoneNumber())
            .trackingId(eventDto.trackingId())
            .onSiteFee(getOnSiteFee(teeBox.getStore().getStoreTeeBoxFees(), teeBox.getType()))
            .build();
    }

    private BigDecimal getOnSiteFee(List<StoreTeeBoxFee> storeTeeBoxFees, TeeBoxType teeBoxType) {
        return storeTeeBoxFees.stream()
            .filter(item -> item.getTeeBoxType() == teeBoxType)
            .map(StoreTeeBoxFee::getOnSiteFee)
            .findFirst()
            .orElse(BigDecimal.ZERO);
    }
}
