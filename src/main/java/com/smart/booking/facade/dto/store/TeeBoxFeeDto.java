package com.smart.booking.facade.dto.store;

import com.smart.booking.domain.common.enums.TeeBoxType;
import com.smart.booking.domain.store.value_object.WeekdayWeekendFee;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "타석 요금")
@RequiredArgsConstructor
public class TeeBoxFeeDto {

    @Schema(description = "타석 종류")
    @NotNull
    private final TeeBoxType teeBoxType;

    @Schema(description = "예약 요금")
    @NotNull
    private final WeekdayWeekendFee bookingFee;


    @Schema(description = "사용 요금")
    @NotNull
    private final WeekdayWeekendFee usageFee;


    @Schema(description = "현장 요금")
    private final BigDecimal onSiteFee;


}
