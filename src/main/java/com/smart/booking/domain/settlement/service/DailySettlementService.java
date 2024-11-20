package com.smart.booking.domain.settlement.service;

import com.smart.booking.domain.settlement.entity.DailySettlement;
import java.time.LocalDate;
import java.util.List;

public interface DailySettlementService {

    /**
     * 일일 정산 조회
     */
    Object getDailySettlement(LocalDate date);

    List<DailySettlement> saveAll(List<DailySettlement> dailySettlementList);
}
