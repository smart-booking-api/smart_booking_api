package com.smart.booking.domain.payment.entity;

import com.smart.booking.domain.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class PaymentPartnerShare extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    // 지분 총 금액
    private BigDecimal calculatedShareAmount;

    // 지분 공급가액
    private BigDecimal supplyAmount;
    // 부가세
    private BigDecimal vatAmount;


}
