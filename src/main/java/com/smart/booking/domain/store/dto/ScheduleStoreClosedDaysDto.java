package com.smart.booking.domain.store.dto;

import com.smart.booking.domain.store.entity.Store;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.NonNull;

public record ScheduleStoreClosedDaysDto(
    @NonNull Store store,
    @NonNull List<OffsetDateTime> dates
) {

}
