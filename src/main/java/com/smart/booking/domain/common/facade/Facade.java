package com.smart.booking.domain.common.facade;

public interface Facade<Dto, Result> {

    Result execute(Dto dto);


}


