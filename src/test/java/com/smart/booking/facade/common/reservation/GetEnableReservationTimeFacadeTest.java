package com.smart.booking.facade.common.reservation;

import com.smart.booking.domain.reservation.service.CommonReservationService;
import com.smart.booking.domain.reservation.service.CommonReservationServiceImpl;
import com.smart.booking.domain.reservation.service.ReservationLockService;
import com.smart.booking.domain.reservation.service.ReservationTimeService;
import com.smart.booking.domain.reservation.service.UserReservationService;
import com.smart.booking.domain.tee_box.service.TeeBoxCommonService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GetEnableReservationTimeFacadeTest {
    private final TeeBoxCommonService teeBoxCommonService;
    private final ReservationTimeService reservationTimeService;
    private final CommonReservationService commonReservationService;
    private final ReservationLockService reservationLockService;
    private final UserReservationService userReservationService;

    public GetEnableReservationTimeFacadeTest(TeeBoxCommonService teeBoxCommonService, ReservationTimeService reservationTimeService,
        CommonReservationServiceImpl commonReservationServiceImpl, ReservationLockService reservationLockService,
        UserReservationService userReservationService) {
        this.teeBoxCommonService = teeBoxCommonService;
        this.reservationTimeService = reservationTimeService;
        this.commonReservationService = commonReservationServiceImpl;
        this.reservationLockService = reservationLockService;
        this.userReservationService = userReservationService;
    }

    @BeforeEach
    void setUp() {
    }
}