package com.smart.booking.domain.payment.service;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.payment.dto.SavePaymentTrackingHistoryDto;
import com.smart.booking.domain.payment.entity.PaymentTrackingHistory;
import com.smart.booking.domain.payment.repositroy.PaymentTrackingHistoryRepository;
import java.math.BigDecimal;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentTrackingHistoryServiceImpl implements PaymentTrackingHistoryService {

    private final PaymentTrackingHistoryRepository paymentTrackingHistoryRepository;

    @Override
    public Object getTrackingInfo(String trackingInfo) {
        return null;
    }

    @Override
    public void saveTrackingHistory(SavePaymentTrackingHistoryDto trackingHistoryDto) {
        paymentTrackingHistoryRepository.save(
            PaymentTrackingHistory.builder()
                .trackingId(trackingHistoryDto.trackingId())
                .teeBoxId(trackingHistoryDto.teeBoxId())
                .timeTableId(trackingHistoryDto.timeTableId())
                .totalAmount(BigDecimal.valueOf(trackingHistoryDto.totalAmount()))
                .paymentStatus(trackingHistoryDto.paymentStatus())
                .build()
        );
    }

    @Override
    public void matchPaymentAndTrackingInfo(@NonNull String trackingId, @NonNull String paymentId) throws Exception {
        var trackingHistory = findById(trackingId);
        trackingHistory.matchPaymentId(paymentId);
    }

    private PaymentTrackingHistory findById(@NonNull String trackingId) throws CommonException {
       return paymentTrackingHistoryRepository.findById(trackingId).orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_ELEMENT));
    }
}
