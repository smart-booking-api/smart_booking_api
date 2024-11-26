package com.smart.booking.domain.store.mapper;

import com.smart.booking.domain.store.dto.ScheduleStoreClosedDaysDto;
import com.smart.booking.domain.store.entity.StoreClosedDay;
import java.util.List;
import lombok.NonNull;

public interface StoreClosedDayMapper {

    static List<StoreClosedDay> toStoreClosedDays(@NonNull ScheduleStoreClosedDaysDto scheduleStoreClosedDaysDto) {
        return scheduleStoreClosedDaysDto.dates().stream()
            .map(date -> StoreClosedDay.builder()
                .store(scheduleStoreClosedDaysDto.store())
                .closedAt(date)
                .build()
            )
            .toList();
    }
}
