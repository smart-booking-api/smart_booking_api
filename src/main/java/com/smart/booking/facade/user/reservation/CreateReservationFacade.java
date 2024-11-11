package com.smart.booking.facade.user.reservation;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.reservation.dto.CreateReservationDto;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.service.StoreUserService;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.facade.event.dto.CompletePaymentEventDto;
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
    private final StoreUserService storeUserService;
    private final MemberService memberService;

    public void createReservation(@NonNull CompletePaymentEventDto eventDto) {
        // 예약
        Store store = storeUserService.getStoreById(eventDto.storeId());
        Member member = memberService.getMemberById(eventDto.memberId());
        userReservationService.createReservation(getCreateReservationDto(store, null, member, eventDto));

        // firebase trackingId 삭제
        Firestore firestore = FirestoreClient.getFirestore();
        firestore.collection(COLLECTION_NAME).document(eventDto.trackingId()).delete();
        log.info("Deleted tracking Id: {}", eventDto.trackingId());
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
