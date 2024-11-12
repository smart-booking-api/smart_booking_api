package com.smart.booking.domain.payment.dto;

import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.relational.core.sql.In;

public record SavePaymentDto(

    @NonNull String impUid,
    @NonNull String merchantUid,
    @NonNull Integer totalAmount,
    @NonNull PaymentStatus paymentStatus,
    @NonNull TeeBox teeBox
) {

}