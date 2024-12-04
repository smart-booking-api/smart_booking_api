package com.smart.booking.domain.common.entity;


import jakarta.persistence.Embeddable;
import java.util.Objects;
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
public class BusinessRegistration {

    private String number;
    private String code;


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final BusinessRegistration point = (BusinessRegistration) o;
        return Objects.equals(number, point.number) &&
            Objects.equals(code, point.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, code);
    }
}

