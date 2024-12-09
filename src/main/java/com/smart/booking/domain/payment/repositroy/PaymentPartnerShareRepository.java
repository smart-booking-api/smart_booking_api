package com.smart.booking.domain.payment.repositroy;

import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.payment.entity.Payment;
import com.smart.booking.domain.payment.entity.PaymentPartnerShare;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentPartnerShareRepository extends JpaRepository<PaymentPartnerShare, String> {

    /**
     * 특정 기간 동안 PaymentPartnerShare 조회
     */
    List<PaymentPartnerShare> findByCreatedAtBetween(OffsetDateTime from, OffsetDateTime to);

    /**
     * 특정 파트너 ID와 날짜 범위로 조회
     */
    List<PaymentPartnerShare> findByPartnerAndCreatedAtBetween(Partner partner, OffsetDateTime from, OffsetDateTime to);


}
