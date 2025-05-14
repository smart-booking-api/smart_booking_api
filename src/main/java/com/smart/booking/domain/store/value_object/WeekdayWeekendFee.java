package com.smart.booking.domain.store.value_object;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@Builder
@NoArgsConstructor(
    access = AccessLevel.PROTECTED
)
@AllArgsConstructor
@Embeddable
public class WeekdayWeekendFee {

    private BigDecimal weekdayFee;
    private BigDecimal weekendFee;

    public @NonNull BigDecimal calculateFee(OffsetDateTime date, int discountRate) {

        final var isWeekend = switch (LocalDateTime.from(date).getDayOfWeek()) {
            case SATURDAY, SUNDAY -> true;
            default -> false;
        };

        final var originFee = isWeekend ? weekendFee : weekdayFee;
        final var discountAmount = originFee.multiply(BigDecimal.valueOf(discountRate / 100));

        return originFee.subtract(discountAmount);
    }
}
