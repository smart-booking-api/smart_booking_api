package com.smart.booking.domain.store.value_object;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(
    access = AccessLevel.PROTECTED
)
@AllArgsConstructor
@Embeddable
public class WeekdayWeekendFee {

    private int weekdayFee;
    private int weekendFee;

    public int calculateFee(OffsetDateTime date, int discountRate) {

        final var isWeekend = switch (LocalDateTime.from(date).getDayOfWeek()) {
            case SATURDAY, SUNDAY -> true;
            default -> false;
        };

        final var originFee = isWeekend ? weekendFee : weekdayFee;

        return originFee - (originFee * discountRate / 100);
    }
}
