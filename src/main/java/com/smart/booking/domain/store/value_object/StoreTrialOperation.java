package com.smart.booking.domain.store.value_object;

import jakarta.persistence.Embeddable;
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
public class StoreTrialOperation {


    private OffsetDateTime trialEndAt;

    private int discountRate;

    public boolean isTrialOperation(OffsetDateTime dateTime) {
        return dateTime.isBefore(trialEndAt) || dateTime.isEqual(trialEndAt);
    }

}
