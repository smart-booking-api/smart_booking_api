package com.smart.booking.domain.payment.dto;

import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class PartnerSettlementDto {

    private Partner partner;

    // 지분 총 금액
    private Integer totalAmount;

    // 지분 공급가액
    private Integer supplyAmount;

    // 부가세
    private Integer vatAmount;

    // 결제 갯수
    private int paymentCount;
}