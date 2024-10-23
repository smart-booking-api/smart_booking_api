package com.smart.booking.domain.settlement.service;

import java.time.LocalDate;

public interface PeriodicSettlementService {

    /**
     * 정산 조회 (기간별)
     */
    Object getPeriodSettlement(LocalDate startDate, LocalDate endDate);
}
