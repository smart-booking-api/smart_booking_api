package com.smart.booking.external.portOne.model;


import com.smart.booking.domain.payment.entity.PaymentStatus;
import lombok.Builder;
import lombok.Setter;

@Builder
public record ExternalCustomDataDto(

    PaymentStatus paymentStatus,
    String memberId,
    String trackingId,
    String teeBoxId,
    String startTimeTableId,
    String endTimeTableId,
    String storeId,
    String storeName,
    String storeAddress,
    String hittingAreaId,
    String startDate,
    String endDate,
    Integer reservationFee,
    Integer useageFee,
    String bookerName,
    String bookerPhone,
    String firebaseUid,
    String failReason,
    int failedAt
) {

    public PaymentStatus getPaymentStatus() {
        return (failedAt != 0) ? PaymentStatus.CANCEL : PaymentStatus.COMPLETE;
    }
}
