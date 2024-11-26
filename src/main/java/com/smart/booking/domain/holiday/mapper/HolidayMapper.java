package com.smart.booking.domain.holiday.mapper;

import com.smart.booking.domain.holiday.entity.Holiday;
import com.smart.booking.external.openapi.model.RestDayDto;
import java.util.List;
import lombok.NonNull;

public interface HolidayMapper {

    static @NonNull Holiday toHoliday(@NonNull RestDayDto restDayDto) {
        return Holiday.builder()
            .name(restDayDto.dateName())
            .date(restDayDto.date())
            .build();

    }

    static @NonNull List<Holiday> toHolidays(@NonNull List<RestDayDto> restDayDtos) {
        return restDayDtos.stream()
            .map(HolidayMapper::toHoliday)
            .toList();
    }
}
