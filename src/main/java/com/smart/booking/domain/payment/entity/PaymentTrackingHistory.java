package com.smart.booking.domain.payment.entity;

import com.smart.booking.domain.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class PaymentTrackingHistory extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String trackingId;

    private String teeBoxId;

    private String timeTableId;

    private String paymentId;

    // 총액 (부가세 포함)
    private BigDecimal totalAmount;
    // 공급가액 (부가세 제외)
    private BigDecimal supplyAmount;
    // 부가세
    private BigDecimal vatAmount;
    // 결제 상태
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public void matchPaymentId(String paymentId) {
        this.paymentId  = paymentId;
    }

}