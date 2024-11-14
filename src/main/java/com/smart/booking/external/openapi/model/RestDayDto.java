package com.smart.booking.external.openapi.model;

import java.time.OffsetDateTime;
import java.util.Map;
import lombok.NonNull;

public record RestDayDto(
    // 날짜
    @NonNull Integer locdate,
    @NonNull Integer seq,
    // dateKind
    @NonNull String dateKind,
    // 휴일여부
    @NonNull String isHoliday,
    // dateName
    @NonNull String dateName
) {

    static public RestDayDto fromMap(Map<?, ?> map) {
        return new RestDayDto(
            (Integer) map.get("locdate"),
            (Integer) map.get("seq"),
            (String) map.get("dateKind"),
            (String) map.get("isHoliday"),
            (String) map.get("dateName")
        );

    }

    public OffsetDateTime date() {
        final var year = Integer.parseInt(locdate.toString().substring(0, 4));
        final var month = Integer.parseInt(locdate.toString().substring(4, 6));
        final var day = Integer.parseInt(locdate.toString().substring(6, 8));

        return OffsetDateTime.of(year, month, day, 0, 0, 0, 0, OffsetDateTime.now().getOffset());
    }

}
