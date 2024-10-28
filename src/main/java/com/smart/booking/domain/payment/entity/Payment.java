package com.smart.booking.domain.payment.entity;

import com.smart.booking.domain.common.entity.BaseEntity;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.List;
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
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String paymentId;

    @OneToOne
    @JoinColumn(name = "tee_box_id", referencedColumnName = "id")
    private TeeBox teeBox;

    // 총액 (부가세 포함)
    private BigDecimal totalAmount;
    // 공급가액 (부가세 제외)
    private BigDecimal supplyAmount;
    // 부가세
    private BigDecimal vatAmount;
    // 결제 상태
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<PaymentPartnerShare> partnerShares;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<PaymentHistory> paymentHistories;

    // 결제 API 통신 이력
    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<PaymentApiHistory> paymentApiHistories;

}