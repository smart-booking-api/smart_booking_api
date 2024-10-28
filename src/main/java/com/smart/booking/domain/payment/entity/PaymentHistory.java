package com.smart.booking.domain.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class PaymentHistory {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    // 총액 (부가세 포함)
    private BigDecimal totalAmount;
    // 공급가액 (부가세 제외)
    private BigDecimal supplyAmount;
    // 부가세
    private BigDecimal vatAmount;
    // 결제 상태
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}