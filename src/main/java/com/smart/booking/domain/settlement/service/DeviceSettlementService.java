package com.smart.booking.domain.settlement.service;


import java.time.LocalDate;

public interface DeviceSettlementService {
    /**
     * 주간 정산 조회
     * */
    Object getDeviceSettlement(Long deviceId, LocalDate date);
}
