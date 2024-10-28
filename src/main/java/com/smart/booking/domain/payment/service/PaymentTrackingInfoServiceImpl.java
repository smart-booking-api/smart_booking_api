package com.smart.booking.domain.payment.service;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.payment.entity.PaymentTrackingHistory;
import com.smart.booking.domain.payment.repositroy.PaymentTrackingHistoryRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentTrackingInfoServiceImpl implements PaymentTrackingInfoService{

    private final PaymentTrackingHistoryRepository paymentTrackingHistoryRepository;

    @Override
    public Object getTrackingInfo(String trackingInfo) {
        return null;
    }

    @Override
    public void saveTrackingInfo(Object trackingInfo) {

    }

    @Override
    public void matchPaymentAndTrackingInfo(@NonNull String trackingId, @NonNull String paymentId) throws CommonException {
        var trackingHistory = findById(trackingId);
        trackingHistory.matchPaymentId(paymentId);
    }

    private PaymentTrackingHistory findById(@NonNull String trackingId) throws CommonException {
       return paymentTrackingHistoryRepository.findById(trackingId).orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_ELEMENT));
    }
}
