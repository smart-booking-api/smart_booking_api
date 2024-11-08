package com.smart.booking.domain.payment.repositroy;

import com.smart.booking.domain.external.dto.ExternalPaymentInfoResponseDto;
import com.smart.booking.domain.external.dto.SearchPaymentInfoRequestDto;

public interface ExternalPaymentRepository {

    String getToken() throws Exception;

    ExternalPaymentInfoResponseDto searchPaymentInfo(SearchPaymentInfoRequestDto request) throws Exception;
}
