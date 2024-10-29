package com.smart.booking.domain.common.facade;

import com.smart.booking.common.exception.CommonException;
import lombok.NonNull;

public interface Facade<Dto,Result> {
     Result exceuete(Dto dto) throws Exception;
}
