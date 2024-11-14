package com.smart.booking.domain.holiday.service;

import com.smart.booking.domain.holiday.entity.Holiday;
import java.util.List;
import lombok.NonNull;

public interface HolidayCommonService {

    @NonNull List<Holiday> getHolidays(int year);


}
