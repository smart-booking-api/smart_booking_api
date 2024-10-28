package com.smart.booking.controller.dto;

import com.smart.booking.domain.common.entity.BusinessRegistration;
import com.smart.booking.domain.common.enums.TeeBoxType;
import com.smart.booking.domain.store.value_object.StoreTrialOperation;
import com.smart.booking.domain.store.value_object.WeekdayWeekendFee;
import java.time.DayOfWeek;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;

public record CompletePaymentRequestDto(
    @NonNull String memberId,
    @NonNull String merchant_uid,
    @NonNull String trackingId,
    @NonNull String teeBoxId,
    @NonNull String timeTableId,
    @NonNull Integer amount
) {

}