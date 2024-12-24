package com.smart.booking.domain.tee_box.dto;

import com.smart.booking.domain.common.enums.TeeBoxType;
import com.smart.booking.domain.device.entity.Device;
import com.smart.booking.domain.store.entity.Store;
import java.util.List;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record CreateTeeBoxDto(
    @NonNull Store store,
    int number,
    @NonNull Device device,
    @NonNull TeeBoxType type,
    @NonNull String screenName,
    @NonNull List<UpsertTeeBoxShareDto> shares
) {

}
