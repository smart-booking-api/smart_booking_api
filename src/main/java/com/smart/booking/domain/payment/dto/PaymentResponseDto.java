package com.smart.booking.domain.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.ArrayList;

public record PaymentResponseDto(
    BigDecimal amount,
    String applyNum,
    String bankCode,
    String bankName,
    String buyerAddr,
    String buyerEmail,
    String buyerName,
    String buyerPostcode,
    String buyerTel,
    int cancelAmount,
    ArrayList<Object> cancelHistories,
    String cancelReason,
    ArrayList<Object> cancelReceiptUrls,
    int cancelledAt,
    String cardCode,
    String cardName,
    String cardNumber,
    int cardQuota,
    int cardType,
    boolean cashReceiptIssued,
    String channel,
    String currency,
    String customData,
    String customerUid,
    String customerUidUsage,
    String embPgProvider,
    boolean escrow,
    String failReason,
    int failedAt,
    String impUid,
    String merchantUid,
    String name,
    int paidAt,
    String payMethod,
    String pgId,
    String pgProvider,
    String pgTid,
    String receiptUrl,
    int startedAt,
    String status,
    String userAgent,
    Object vbankCode,
    int vbankDate,
    String vbankHolder,
    int vbankIssuedAt,
    String vbankName,
    String vbankNum
) {

}
