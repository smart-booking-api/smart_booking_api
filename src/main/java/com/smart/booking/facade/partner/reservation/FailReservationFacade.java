package com.smart.booking.facade.partner.reservation;

import com.smart.booking.common.firebase.FirebaseComponent;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.reservation.dto.UpsertReservationLockDto;
import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.entity.ReservationTimeCode;
import com.smart.booking.domain.reservation.service.ReservationLockService;
import com.smart.booking.domain.reservation.service.ReservationTimeService;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.facade.dto.reservation.ReservationFirebaseStatusDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FailReservationFacade {
    private final UserReservationService userReservationService;
    private final MemberService memberService;
    private final FirebaseComponent firebaseComponent;
    private final ReservationLockService reservationLockService;
    private final ReservationTimeService reservationTimeService;
    private final static String COLLECTION_NAME = "booking";

    public void execute(String trackingId, String memberId) {
        // 예약취소
        Reservation reservation = userReservationService.getReservationByTrackingId(trackingId);
        Member member = memberService.getMemberById(memberId);
        userReservationService.cancelReservation(reservation.getId(), memberId, member.getType());

        // firebase 처리상태 업데이트
        firebaseComponent.updateDocument(COLLECTION_NAME, trackingId,
            new ReservationFirebaseStatusDto(trackingId, memberId, PaymentStatus.CANCEL, reservation.getReservationNo()));

        // 선점락 제거
        deleteReservationLock(reservation, memberId);
    }

    private void deleteReservationLock(Reservation reservation, String memberId) {
        List<ReservationTimeCode> reservationTimeCodes = reservationTimeService.getReservationTimeBetweenStartAndEnd(reservation.getStartTimeId(), reservation.getEndTimeId());

        for (ReservationTimeCode reservationTime : reservationTimeCodes) {
            UpsertReservationLockDto upsertReservationLockDto = createLockDto(reservation.getTeeBox().getId(), reservation.getReservationDateString(), reservationTime, memberId);
            reservationLockService.deleteReservationLock(upsertReservationLockDto);
        }
    }

    private UpsertReservationLockDto createLockDto(String teeBoxId, String date, ReservationTimeCode timeCode, String memberId) {
        return UpsertReservationLockDto.builder()
            .teeBoxId(teeBoxId)
            .lockTimeId(timeCode.getId())
            .date(date)
            .memberId(memberId)
            .build();
    }
}
