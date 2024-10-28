package com.smart.booking.domain.settlement.entity;

import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class DailySettlement {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    //일자
    private LocalDate startDate;
    @ManyToOne
    private Partner partner;

    @OneToOne
    @JoinColumn(name = "tee_box_id", referencedColumnName = "id")
    private TeeBox teeBox;

    private BigDecimal totalAmount; // 총액 (부가세 포함)
    private BigDecimal supplyAmount; // 공급가액 (부가세 제외)
    private BigDecimal vatAmount; // 부가세

    @PrePersist
    public void calculateAmounts() {
        if (totalAmount != null) {
            // 부가세율 설정
            BigDecimal vatRate = BigDecimal.valueOf(0.1);

            // 공급가액 계산
            this.supplyAmount = totalAmount.divide(BigDecimal.ONE.add(vatRate), 2, RoundingMode.HALF_UP);

            // 부가세 계산
            this.vatAmount = totalAmount.subtract(supplyAmount);
        }
    }
}