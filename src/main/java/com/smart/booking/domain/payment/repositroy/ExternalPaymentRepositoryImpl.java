package com.smart.booking.domain.payment.repositroy;


import com.smart.booking.external.portOne.client.PortOneClient;
import com.smart.booking.external.portOne.model.CancelPaymentRequestDto;
import com.smart.booking.external.portOne.model.ExternalCancelPaymentResponseDto;
import com.smart.booking.external.portOne.model.ExternalPaymentInfoResponseDto;
import com.smart.booking.external.portOne.model.SearchPaymentInfoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalPaymentRepositoryImpl implements ExternalPaymentRepository {

    private final PortOneClient portOneClient;

    @Override
    public String getToken() {
        var token = portOneClient.getToken();
        return token.response().accessToken();
    }

    //TODO token 관리 개선
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
