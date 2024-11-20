package com.smart.booking.domain.settlement.service;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.common.util.JsonUtil;
import com.smart.booking.domain.external.dto.CancelPaymentRequestDto;
import com.smart.booking.domain.external.dto.ExternalCustomDataDto;
import com.smart.booking.domain.external.dto.PaymentAnnotationDto;
import com.smart.booking.domain.external.dto.SearchPaymentInfoRequestDto;
import com.smart.booking.domain.payment.dto.PaymentResponseDto;
import com.smart.booking.domain.payment.dto.SavePaymentDto;
import com.smart.booking.domain.payment.entity.Payment;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.payment.repositroy.ExternalPaymentRepository;
import com.smart.booking.domain.payment.repositroy.PaymentRepository;
import com.smart.booking.domain.payment.service.PaymentService;
import com.smart.booking.domain.settlement.entity.DailySettlement;
import com.smart.booking.domain.settlement.repository.DailySettlementRepository;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.NonNull;
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
