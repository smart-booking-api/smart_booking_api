package com.smart.booking.facade.dto.reservation;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateReservationLockDto(
    @NotBlank(message = "타석 정보가 누락되었습니다.") String teeBoxId,
    @NotBlank(message = "시작시간이 누락되었습니다.") String startTimeId,
    @NotBlank(message = "종료시간이 누락되었습니다.") String endTimeId
) {}
