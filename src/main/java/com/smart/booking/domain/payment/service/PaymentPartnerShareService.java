package com.smart.booking.domain.payment.service;

import com.smart.booking.domain.external.dto.CancelPaymentRequestDto;
import com.smart.booking.domain.external.dto.ExternalCustomDataDto;
import com.smart.booking.domain.external.dto.PaymentAnnotationDto;
import com.smart.booking.domain.payment.dto.PaymentResponseDto;
import com.smart.booking.domain.payment.dto.SavePaymentDto;
import com.smart.booking.domain.payment.entity.Payment;
import com.smart.booking.domain.payment.entity.PaymentPartnerShare;
import java.time.OffsetDateTime;
import java.util.List;

public interface PaymentPartnerShareService {

    List<PaymentPartnerShare> saveAll(List<PaymentPartnerShare> paymentPartnerShares);

}
