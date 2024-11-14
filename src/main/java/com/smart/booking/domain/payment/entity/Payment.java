package com.smart.booking.domain.payment.entity;

import static com.smart.booking.common.enums.ResponseCode.NOT_REFUNDABLE_STATUS_ERROR;

import com.smart.booking.common.exception.CommonException;
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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    private Integer totalAmount;
    // 공급가액 (부가세 제외)
    private Integer supplyAmount;
    // 부가세
    private Integer vatAmount;
    // 결제 상태
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    //포트원 거래고유 번호
    private String impUid;
    //고객사 주문번호
    private String merchantUid;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<PaymentPartnerShare> partnerShares;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<PaymentHistory> paymentHistories;

    // 결제 API 통신 이력
    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<PaymentApiHistory> paymentApiHistories;

    public void updatePaymentStatus(PaymentStatus status) {
        this.paymentStatus = status;
    }

    public Boolean isRefundable() {
        return PaymentStatus.COMPLETE.equals(paymentStatus);
    }

    public Integer calculateRefundableAmount(LocalDate refundRequestDate, LocalDate reservationDate) {
        long daysUntilReservation = ChronoUnit.DAYS.between(refundRequestDate, reservationDate);

        if (daysUntilReservation >= 2) {
            return totalAmount; // 전액 환불
        } else if (daysUntilReservation == 1) {
            return totalAmount / 2; // 50% 환불
        } else {
            throw new CommonException(NOT_REFUNDABLE_STATUS_ERROR);
        }
    }

}