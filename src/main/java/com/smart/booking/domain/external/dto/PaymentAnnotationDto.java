package com.smart.booking.domain.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import lombok.ToString;

public record PaymentAnnotationDto(
    @JsonProperty("amount")
    BigDecimal amount,
    @JsonProperty("apply_num")
    String applyNum,
    @JsonProperty("bank_code")
    String bankCode,
    @JsonProperty("bank_name")
    String bankName,
    @JsonProperty("buyer_addr")
    String buyerAddr,
    @JsonProperty("buyer_email")
    String buyerEmail,
    @JsonProperty("buyer_name")
    String buyerName,
    @JsonProperty("buyer_postcode")
    String buyerPostcode,
    @JsonProperty("buyer_tel")
    String buyerTel,
    @JsonProperty("cancel_amount")
    int cancelAmount,
    @JsonProperty("cancel_history")
    ArrayList<Object> cancelHistories,
    @JsonProperty("cancel_reason")
    String cancelReason,
    @JsonProperty("cancel_receipt_urls")
    ArrayList<Object> cancelReceiptUrls,
    @JsonProperty("cancelled_at")
    int cancelledAt,
    @JsonProperty("card_code")
    String cardCode,
    @JsonProperty("card_name")
    String cardName,
    @JsonProperty("card_number")
    String cardNumber,
    @JsonProperty("card_quota")
    int cardQuota,
    @JsonProperty("card_type")
    int cardType,
    @JsonProperty("cash_receipt_issued")
    boolean cashReceiptIssued,
    @JsonProperty("channel")
    String channel,
    @JsonProperty("currency")
    String currency,
    @JsonProperty("custom_data")
    String customData,
    @JsonProperty("customer_uid")
    String customerUid,
    @JsonProperty("customer_uid_usage")
    String customerUidUsage,
    @JsonProperty("emb_pg_provider")
    String embPgProvider,
    @JsonProperty("escrow")
    boolean escrow,
    @JsonProperty("fail_reason")
    String failReason,
    @JsonProperty("failed_at")
    int failedAt,
    @JsonProperty("imp_uid")
    String impUid,
    @JsonProperty("merchant_uid")
    String merchantUid,
    @JsonProperty("name")
    String name,
    @JsonProperty("paid_at")
    int paidAt,
    @JsonProperty("pay_method")
    String payMethod,
    @JsonProperty("pg_id")
    String pgId,
    @JsonProperty("pg_provider")
    String pgProvider,
    @JsonProperty("pg_tid")
    String pgTid,
    @JsonProperty("receipt_url")
    String receiptUrl,
    @JsonProperty("started_at")
    int startedAt,
    @JsonProperty("status")
    String status,
    @JsonProperty("user_agent")
    String userAgent,
    @JsonProperty("vbank_code")
    Object vbankCode,
    @JsonProperty("vbank_date")
    int vbankDate,
    @JsonProperty("vbank_holder")
    String vbankHolder,
    @JsonProperty("vbank_issued_at")
    int vbankIssuedAt,
    @JsonProperty("vbank_name")
    String vbankName,
    @JsonProperty("vbank_num")
    String vbankNum
) {

}
