package com.smart.booking.facade.dto.reservation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public record GetReservationTime(
    @NotBlank(message = "타석정보가 누락되었습니다.")
    @Schema(description = "타석 ID")
    String teeBoxId,
    @NotBlank(message = "조회날짜가 누락되었습니다.")
    @Schema(description = "조회날짜")
    String reservationDate
) {

}
