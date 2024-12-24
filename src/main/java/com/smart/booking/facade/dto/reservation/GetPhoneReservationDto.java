package com.smart.booking.facade.dto.reservation;

import com.smart.booking.domain.reservation.dto.PhoneReservationDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GetPhoneReservationDto {
    @Schema(description = "예약번호")
    private int reservationNo;

    @Schema(description = "예약자 이름")
    private String reservationName;

    @Schema(description = "예약일시")
    private String reservationDateTime;

    @Schema(description = "지점, 장비번호")
    private String storeNameNo;

    public GetPhoneReservationDto(PhoneReservationDto phoneReservationDto) {
        DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        String reservationDate = formatter.format(phoneReservationDto.getReservationDate());
        this.reservationNo = phoneReservationDto.getReservationNo();
        this.reservationName = phoneReservationDto.getReservationName();
        this.reservationDateTime = reservationDate.concat(phoneReservationDto.getReservationTime());
        this.storeNameNo = phoneReservationDto.getStoreNameNo();
    }
}
