package com.smart.booking.domain.payment.entity;

import com.smart.booking.domain.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class PaymentApiHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    // API 요청의 상태 (성공, 실패 등)
    @Enumerated(EnumType.STRING)
    private ApiResultStatus apiResultStatus;
    // 결제 상태
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;


    private LocalDate apiCallDate;
    private String apiEndpoint;
    private String requestPayload;
    private String responsePayload;
    private String status; // 성공, 실패 등

    // 기타 API 통신 기록 정보
}