package com.smart.booking.domain.settlement.service;


public interface MonthlySettlementService {
    /**
     * 월간 정산 조회
     * */
    Object getMonthlySettlement(int yyyyMM);
}
