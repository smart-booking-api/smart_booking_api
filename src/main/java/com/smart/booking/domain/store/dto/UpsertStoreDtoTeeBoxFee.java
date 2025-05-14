package com.smart.booking.domain.store.dto;

import com.smart.booking.domain.common.enums.TeeBoxType;
import com.smart.booking.domain.store.value_object.WeekdayWeekendFee;
import java.math.BigDecimal;
import lombok.NonNull;

public record UpsertStoreDtoTeeBoxFee(
    @NonNull TeeBoxType teeBoxType,
    @NonNull WeekdayWeekendFee bookingFee,
    @NonNull WeekdayWeekendFee usageFee,
    BigDecimal onSiteFee
) {

}
