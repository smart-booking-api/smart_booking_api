package com.smart.booking.domain.payment.service;

import com.smart.booking.domain.payment.entity.PaymentPartnerShare;
import java.util.List;

public interface PaymentPartnerShareService {

    List<PaymentPartnerShare> saveAll(List<PaymentPartnerShare> paymentPartnerShares);

}
