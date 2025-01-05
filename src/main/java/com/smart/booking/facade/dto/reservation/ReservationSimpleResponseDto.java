package com.smart.booking.facade.dto.reservation;

import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.domain.reservation.service.ReservationTimeService;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ReservationSimpleResponseDto {
    @Schema(description = "예약번호")
    private int reservationNo;
    @Schema(description = "예약날짜")
    private LocalDate reservationDate;
    @Schema(description = "예약시작시간")
    private String startTime;
    @Schema(description = "예약종료시간")
    private String endTime;
    @Schema(description = "매장명")
    private String storeName;
    @Schema(description = "스크린번호")
    private String teeBoxNumber;

    public ReservationSimpleResponseDto(Reservation reservation, ReservationTimeService reservationTimeService) {
        this.reservationNo = reservation.getReservationNo();
        this.reservationDate = reservation.getReservationDate();
        this.startTime = reservation.getStartTime(reservationTimeService);
        this.endTime = reservation.getEndTime(reservationTimeService);;
        this.storeName = reservation.getStore().getName();
        this.teeBoxNumber = reservation.getTeeBox().getName();
    }
}
