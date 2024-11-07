package com.smart.booking.domain.user.value_object;

import jakarta.persistence.Embeddable;
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
public class UserPolicyAgreement {

    private boolean pushAgreement;

    private boolean emailAgreement;

    private boolean smsAgreement;

}
