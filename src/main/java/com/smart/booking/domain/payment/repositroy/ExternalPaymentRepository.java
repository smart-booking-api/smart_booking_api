package com.smart.booking.domain.payment.repositroy;

import com.smart.booking.external.portOne.model.CancelPaymentRequestDto;
import com.smart.booking.external.portOne.model.ExternalCancelPaymentResponseDto;
import com.smart.booking.external.portOne.model.ExternalPaymentInfoResponseDto;
import com.smart.booking.external.portOne.model.SearchPaymentInfoRequestDto;

public interface ExternalPaymentRepository {

    String getToken() throws Exception;

    ExternalPaymentInfoResponseDto searchPaymentInfo(SearchPaymentInfoRequestDto request);

    ExternalCancelPaymentResponseDto cancelPayment(CancelPaymentRequestDto request);
}
