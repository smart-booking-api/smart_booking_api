package com.smart.booking.domain.settlement.service;

import java.time.LocalDate;

public interface PartnerSettlementService {
    /**
     * 파트너별 정산 조회
     * */
    Object getPartnerSettlement(Long partnerId, LocalDate date);
}
