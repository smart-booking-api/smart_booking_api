package com.smart.booking.domain.settlement.service;

import java.time.LocalDate;

public interface DailySettlementService {
    /**
     * 일일 정산 조회
     * */
    Object getDailySettlement(LocalDate date);
}
