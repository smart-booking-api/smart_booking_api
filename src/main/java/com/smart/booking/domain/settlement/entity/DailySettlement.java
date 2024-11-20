package com.smart.booking.domain.settlement.entity;

import com.smart.booking.common.util.PriceUtil;
import com.smart.booking.domain.common.entity.BaseEntity;
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
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DailySettlement extends BaseEntity {

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

    private Integer totalAmount; // 총액 (부가세 포함)
    private Integer supplyAmount; // 공급가액 (부가세 제외)
    private Integer vatAmount; // 부가세

    @PrePersist
    public void calculateAmounts() {
        if (totalAmount != null) {
            PriceUtil.AmountResult result = PriceUtil.calculateAmounts(totalAmount);

            this.supplyAmount = result.getSupplyAmount();
            this.vatAmount = result.getVatAmount();
        }
    }

    @Data
    @AllArgsConstructor
    public static class DailySettlementKey {

        private LocalDate startDate;
        private Partner partner;
        private TeeBox teeBox;

        // Equals and hashCode are critical for using this as a Map key.
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            DailySettlementKey that = (DailySettlementKey) o;
            return Objects.equals(startDate, that.startDate) &&
                Objects.equals(partner, that.partner) &&
                Objects.equals(teeBox, that.teeBox);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, partner, teeBox);
        }
    }
}