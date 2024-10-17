package com.smart.booking.domain.common.facade;

import lombok.NonNull;

public interface Facade<Dto,Result> {
     Result exceuete(Dto dto);
}
