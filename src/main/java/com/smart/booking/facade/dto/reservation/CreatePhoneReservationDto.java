package com.smart.booking.facade.dto.reservation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record CreatePhoneReservationDto(
    @NotBlank(message = "타석 정보가 누락되었습니다.")
    @Schema(description = "타석ID")
    String teeBoxId,
    @NotBlank(message = "날짜 정보가 누락되었습니다.")
    @Pattern(regexp = "^[0-9]+$", message = "숫자만 입력 가능합니다.")
    @Schema(description = "예약날짜")
    String reservationDate,
    @NotBlank(message = "시작시간이 누락되었습니다.")
    @Schema(description = "시작시간 ID")
    @NonNull String startTimeTableId,
    @NotBlank(message = "종료시간이 누락되었습니다.")
    @Schema(description = "종료시간 ID")
    @NonNull String endTimeTableId,
    @NotBlank(message = "예약자 이름이 누락되었습니다.")
    @Schema(description = "예약자 이름")
    @NonNull String reservationUserName,
    @NotBlank(message = "예약자 휴대폰번호가 누락되었습니다.")
    @Schema(description = "예약자 휴대폰번호")
    @NonNull String reservationUserPhoneNumber
) {
}
