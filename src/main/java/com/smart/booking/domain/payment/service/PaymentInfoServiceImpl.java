package com.smart.booking.domain.payment.service;

import com.smart.booking.domain.payment.dto.SavePaymentDto;
import com.smart.booking.domain.payment.entity.Payment;
import com.smart.booking.domain.payment.repositroy.PaymentRepository;
import java.math.BigDecimal;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentInfoServiceImpl implements PaymentInfoService{

    private final PaymentRepository paymentRepository;

    @Override
    public Object getPaymentInfo(@NonNull String paymentId) {
        return null;
    }

    @Override
    public Payment savePaymentCompleteInfo(SavePaymentDto request) {
        return paymentRepository.save(
            Payment.builder()
                .teeBox(request.teeBox())
                .totalAmount(BigDecimal.valueOf(request.totalAmount()))
                .supplyAmount(BigDecimal.valueOf(request.totalAmount()))
                .paymentStatus(request.paymentStatus())
            .build()
        );
    }

    @Override
    public void savePaymentCancelInfo(String paymentId) {

    }

}
