package com.smart.booking.domain.holiday.service;

import com.smart.booking.domain.holiday.dto.ScrapHolidaysDto;
import com.smart.booking.domain.holiday.entity.Holiday;
import com.smart.booking.domain.holiday.mapper.HolidayMapper;
import com.smart.booking.domain.holiday.repository.HolidayRepository;
import com.smart.booking.external.openapi.OpenApiService;
import com.smart.booking.external.openapi.model.GetRestDayInfoDto;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class HolidayPartnerServiceImpl extends HolidayCommonServiceImpl implements HolidayPartnerService {

    private final HolidayRepository holidayRepository;
    private final OpenApiService openApiService;

    public HolidayPartnerServiceImpl(
        @NonNull HolidayRepository holidayRepository,
        @NonNull OpenApiService openApiService
    ) {
        super(holidayRepository);
        this.holidayRepository = holidayRepository;
        this.openApiService = openApiService;
    }


    /**
     * 휴일 정보를 스크래핑하여 저장한다.
     * 년 월 정보를 받아서 해당 년 월의 휴일 정보를 스크래핑하여 저장한다.
     * 월이 null일 경우 해당 년도 전체 휴일 정보를 스크래핑하여 저장한다.
     *
     * @param scrapHolidaysDto 년 월 정보
     *
     * @return 휴일 정보
     */
    @Override
    public @NonNull List<Holiday> scrapeHolidays(@NonNull ScrapHolidaysDto scrapHolidaysDto) {
        final var restDayDtos = openApiService.getRestDayInfo(
            new GetRestDayInfoDto(
                scrapHolidaysDto.year(),
                scrapHolidaysDto.month()
            )
        );

        final var existentHolidays = scrapHolidaysDto.month() == null
            ? getHolidaysWithYear(scrapHolidaysDto.year()).stream().map(Holiday::getDate).toList()
            : getHolidaysWithYearMonth(scrapHolidaysDto.year(), scrapHolidaysDto.month()).stream().map(Holiday::getDate).toList();

        final var newHolidays = HolidayMapper.toHolidays(
            restDayDtos.stream()
                .filter(restDayDto -> !existentHolidays.contains(restDayDto.date()))
                .toList()
        );

        return holidayRepository.saveAll(newHolidays);
    }


    private List<Holiday> getHolidaysWithYear(int year) {
        final var startOfYear = OffsetDateTime.of(year, 1, 1, 0, 0, 0, 0, OffsetDateTime.now().getOffset());
        final var endOfYear = OffsetDateTime.of(year, 12, 31, 0, 0, 0, 0, OffsetDateTime.now().getOffset());

        return holidayRepository.findAllByDateBetween(startOfYear, endOfYear);
    }

    private List<Holiday> getHolidaysWithYearMonth(int year, int month) {
        final var startOfMonth = OffsetDateTime.of(year, month, 1, 0, 0, 0, 0, OffsetDateTime.now().getOffset());
        final var endOfMonth = OffsetDateTime.of(year, month, 1, 0, 0, 0, 0, OffsetDateTime.now().getOffset())
            .plusMonths(1)
            .minusDays(1);

        return holidayRepository.findAllByDateBetween(startOfMonth, endOfMonth);
    }
}
