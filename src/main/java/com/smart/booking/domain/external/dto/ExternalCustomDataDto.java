package com.smart.booking.domain.external.dto;


import lombok.Builder;

@Builder
public record ExternalCustomDataDto(

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
