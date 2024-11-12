package com.smart.booking.domain.payment.service;

import com.smart.booking.domain.payment.dto.SavePaymentHistoryDto;
import com.smart.booking.domain.payment.entity.PaymentHistory;
import com.smart.booking.domain.payment.repositroy.PaymentHistoryRepository;
import com.smart.booking.domain.payment.repositroy.PaymentRepository;
import java.math.BigDecimal;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;

    @Override
    public void savePaymentHistoryLog(SavePaymentHistoryDto historyDto) {
        paymentHistoryRepository.save(
            PaymentHistory.builder()
                .payment(historyDto.payment())
                .totalAmount(BigDecimal.valueOf(historyDto.totalAmount()))
                .paymentStatus(historyDto.paymentStatus())
                .build()
        );
    }

}
