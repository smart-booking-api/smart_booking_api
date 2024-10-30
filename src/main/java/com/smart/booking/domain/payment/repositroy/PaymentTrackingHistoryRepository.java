package com.smart.booking.domain.payment.repositroy;

import com.smart.booking.domain.payment.entity.PaymentTrackingHistory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTrackingHistoryRepository extends JpaRepository<PaymentTrackingHistory, String> {

    Optional<PaymentTrackingHistory> findByTrackingId(String trackingId);
}
