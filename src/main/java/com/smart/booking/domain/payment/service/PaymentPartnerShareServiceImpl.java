package com.smart.booking.domain.payment.service;

import com.smart.booking.domain.payment.dto.SavePaymentHistoryDto;
import com.smart.booking.domain.payment.entity.PaymentHistory;
import com.smart.booking.domain.payment.entity.PaymentPartnerShare;
import com.smart.booking.domain.payment.repositroy.PaymentHistoryRepository;
import com.smart.booking.domain.payment.repositroy.PaymentPartnerShareRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentPartnerShareServiceImpl implements PaymentPartnerShareService {

    private final PaymentPartnerShareRepository paymentPartnerShareRepository;


    @Override
    public List<PaymentPartnerShare> saveAll(List<PaymentPartnerShare> paymentPartnerShares) {
        return paymentPartnerShareRepository.saveAll(paymentPartnerShares);
    }
}
