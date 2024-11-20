package com.smart.booking.domain.settlement.repository;

import com.smart.booking.domain.payment.entity.Payment;
import com.smart.booking.domain.settlement.entity.DailySettlement;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailySettlementRepository extends JpaRepository<DailySettlement, String> {


}
