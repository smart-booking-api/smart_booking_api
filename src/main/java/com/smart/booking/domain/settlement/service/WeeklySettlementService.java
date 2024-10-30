package com.smart.booking.domain.settlement.service;


public interface WeeklySettlementService {
    /**
     * 주간 정산 조회
     * */
    Object getWeeklySettlement(int weekOfYear);
}
