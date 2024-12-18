package com.smart.booking.external.portOne.model;


import lombok.Builder;

@Builder
public record ExternalCustomDataDto(

    String memberId,
    String trackingId,
    String teeBoxId,
    String timeTableId,
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
    String firebaseUid
) {

}
