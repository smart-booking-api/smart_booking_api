package com.smart.booking.domain.store.dto;

import com.smart.booking.domain.common.enums.Region;

public record GetStoresDto(
    String name,
    Region region,
    int pageSize,
    String cursor
) {

}
