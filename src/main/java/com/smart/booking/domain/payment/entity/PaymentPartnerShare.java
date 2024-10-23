package com.smart.booking.domain.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class PaymentPartnerShare {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @ManyToOne
    private Payment payment;

    // 지분 총 금액
    private BigDecimal calculatedShareAmount;

    // 지분 공급가액
    private BigDecimal supplyAmount;


}
