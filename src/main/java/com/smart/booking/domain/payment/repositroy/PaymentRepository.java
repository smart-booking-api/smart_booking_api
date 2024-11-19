package com.smart.booking.domain.payment.repositroy;

import com.smart.booking.domain.payment.entity.Payment;
import com.smart.booking.domain.payment.entity.PaymentTrackingHistory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {

    Payment findByImpUidAndMerchantUid(String impUid, String merchantUid);

    List<Payment> findByCreatedAtBetween(OffsetDateTime createdAt, OffsetDateTime createdAt2);

}
