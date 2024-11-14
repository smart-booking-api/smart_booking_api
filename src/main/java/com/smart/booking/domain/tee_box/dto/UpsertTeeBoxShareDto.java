package com.smart.booking.domain.tee_box.dto;

import com.smart.booking.domain.partner.entity.Partner;
import lombok.NonNull;

public record UpsertTeeBoxShareDto(
    @NonNull Partner partner,
    int share
) {

}
