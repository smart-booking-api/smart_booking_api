package com.smart.booking.domain.holiday.service;

import com.smart.booking.domain.holiday.entity.Holiday;
import com.smart.booking.domain.holiday.repository.HolidayRepository;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
abstract class HolidayCommonServiceImpl implements HolidayCommonService {

    private final HolidayRepository holidayRepository;

    @Override
    public @NonNull List<Holiday> getHolidays(int year) {
        return holidayRepository.findAllByDateBetween(
            OffsetDateTime.of(year, 1, 1, 0, 0, 0, 0, OffsetDateTime.now().getOffset()),
            OffsetDateTime.of(year, 12, 31, 0, 0, 0, 0, OffsetDateTime.now().getOffset())
        );
    }
}
