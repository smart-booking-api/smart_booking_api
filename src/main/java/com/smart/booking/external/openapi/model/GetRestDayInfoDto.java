package com.smart.booking.external.openapi.model;

import java.util.Optional;

public record GetRestDayInfoDto(
    int solYear,
    Integer solMonth

) {


    public Optional<String> getSolMonth() {
        if (solMonth == null) {
            return Optional.empty();
        }

        return Optional.of(solMonth < 10 ? "0" + solMonth : solMonth.toString());
    }

}
