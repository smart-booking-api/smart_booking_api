package com.smart.booking.facade.dto.reservation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record CreateReservationLockDto(
    @NotBlank(message = "타석 정보가 누락되었습니다.")
    @Schema(description = "타석ID")
    String teeBoxId,
    @NotBlank(message = "날짜 정보가 누락되었습니다.")
    @Pattern(regexp = "^[0-9]+$", message = "숫자만 입력 가능합니다.")
    @Schema(description = "예약날짜")
    String date,
    @NotBlank(message = "시작시간이 누락되었습니다.")
    @Schema(description = "시작시간 ID")
    String startTimeId,
    @NotBlank(message = "종료시간이 누락되었습니다.")
    @Schema(description = "종료시간 ID")
    String endTimeId,
    @NotBlank(message = "trackingId가 누락되었습니다.")
    @Schema(description = "tracking ID")
    String trackingId,
    @Schema(description = "매장식별 ID")
    String posId
) {}
