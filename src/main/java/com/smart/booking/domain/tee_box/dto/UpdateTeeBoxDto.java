package com.smart.booking.domain.tee_box.dto;

import com.smart.booking.domain.common.enums.TeeBoxType;
import com.smart.booking.domain.device.entity.Device;
import java.util.List;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record UpdateTeeBoxDto(
    int number,
    @NonNull Device device,
    @NonNull TeeBoxType type,
    @NonNull String screenName,
    @NonNull List<UpsertTeeBoxShareDto> shares
) {

}
