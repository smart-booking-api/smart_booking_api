package com.smart.booking.domain.settlement.service;

import com.smart.booking.domain.settlement.entity.DailySettlement;
import com.smart.booking.domain.settlement.repository.DailySettlementRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailySettlementServiceImpl implements DailySettlementService {

    private final DailySettlementRepository dailySettlementRepository;


    @Override
    public Object getDailySettlement(LocalDate date) {
        return null;
    }

    @Override
    public List<DailySettlement> saveAll(List<DailySettlement> dailySettlementList) {
        return dailySettlementRepository.saveAll(dailySettlementList);
    }
}
