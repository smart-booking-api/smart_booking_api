package com.smart.booking.domain.payment.service;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.common.util.JsonUtil;
import com.smart.booking.domain.external.dto.CancelPaymentRequestDto;
import com.smart.booking.domain.external.dto.ExternalCustomDataDto;
import com.smart.booking.domain.external.dto.ExternalPaymentInfoResponseDto;
import com.smart.booking.domain.external.dto.PaymentAnnotationDto;
import com.smart.booking.domain.external.dto.SearchPaymentInfoRequestDto;
import com.smart.booking.domain.payment.dto.SavePaymentDto;
import com.smart.booking.domain.payment.entity.Payment;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.payment.repositroy.ExternalPaymentRepository;
import com.smart.booking.domain.payment.repositroy.PaymentRepository;
import java.math.BigDecimal;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentInfoServiceImpl implements PaymentInfoService {

    private final PaymentRepository paymentRepository;
    private final ExternalPaymentRepository externalPaymentRepository;

    @Override
    public Payment getPaymentInfo(@NonNull String paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_ELEMENT));
    }

    @Override
    public ExternalCustomDataDto getExternalPaymentInfo(@NonNull String merchantUid) {
        var request = new SearchPaymentInfoRequestDto(merchantUid, "-started");
        var paymentInfo = externalPaymentRepository.searchPaymentInfo(request).response();
        return JsonUtil.convertToObject(paymentInfo.customData(), ExternalCustomDataDto.class);
    }

    @Override
    public PaymentAnnotationDto cancelPayment(CancelPaymentRequestDto request) {
        var cancelResponse = externalPaymentRepository.cancelPayment(request);
        return cancelResponse.response();
    }

    @Override
    public Payment savePaymentCompleteInfo(@NonNull SavePaymentDto request) {
        return paymentRepository.save(
            Payment.builder()
                .impUid(request.impUid())
                .merchantUid(request.merchantUid())
                .teeBox(request.teeBox())
                .totalAmount(BigDecimal.valueOf(request.totalAmount()))
                .supplyAmount(BigDecimal.valueOf(request.totalAmount()))
                .paymentStatus(request.paymentStatus())
                .build()
        );
    }

    @Override
    public void savePaymentCancelInfo(@NonNull Payment payment) {
        payment.updatePaymentStatus(PaymentStatus.REFUND);
    }

}
