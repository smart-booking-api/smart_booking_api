package com.smart.booking.domain.holiday.service;

import com.smart.booking.domain.holiday.dto.ScrapHolidaysDto;
import com.smart.booking.domain.holiday.entity.Holiday;
import java.util.List;
import lombok.NonNull;

public interface HolidayPartnerService extends HolidayCommonService {

    @NonNull List<Holiday> scrapeHolidays(@NonNull ScrapHolidaysDto scrapHolidaysDto);

}
