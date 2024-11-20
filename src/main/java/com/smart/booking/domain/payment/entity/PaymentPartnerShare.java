package com.smart.booking.domain.payment.entity;

import com.smart.booking.common.util.PriceUtil;
import com.smart.booking.domain.common.entity.BaseEntity;
import com.smart.booking.domain.partner.entity.Partner;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class PaymentPartnerShare extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    // 지분 총 금액
    private Integer totalAmount;

    // 지분 공급가액
    private Integer supplyAmount;
    // 부가세
    private Integer vatAmount;

    @PrePersist
    public void calculateAmounts() {
        if (totalAmount != null) {
            PriceUtil.AmountResult result = PriceUtil.calculateAmounts(totalAmount);

            this.supplyAmount = result.getSupplyAmount();
            this.vatAmount = result.getVatAmount();
        }
    }
}
