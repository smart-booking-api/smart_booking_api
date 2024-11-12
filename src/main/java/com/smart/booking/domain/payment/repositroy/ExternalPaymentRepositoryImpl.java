package com.smart.booking.domain.payment.repositroy;


import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.external.client.PortOneClient;
import com.smart.booking.domain.external.dto.CancelPaymentRequestDto;
import com.smart.booking.domain.external.dto.ExternalCancelPaymentResponseDto;
import com.smart.booking.domain.external.dto.ExternalPaymentInfoResponseDto;
import com.smart.booking.domain.external.dto.SearchPaymentInfoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

@Service
@RequiredArgsConstructor
public class ExternalPaymentRepositoryImpl implements ExternalPaymentRepository {

    private final PortOneClient portOneClient;

    @Override
    public String getToken() {
        var token = portOneClient.getToken();
        return token.response().accessToken();
    }

    @Override
    public ExternalPaymentInfoResponseDto searchPaymentInfo(SearchPaymentInfoRequestDto request) {
        var token = getToken();
        return portOneClient.searchPayment(token, request);
    }

    @Override
    public ExternalCancelPaymentResponseDto cancelPayment(CancelPaymentRequestDto request) {
        var token = getToken();
        return portOneClient.cancelPayment(token, request);
    }
}
