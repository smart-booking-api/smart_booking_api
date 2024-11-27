package com.smart.booking.facade.event.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreatePartnerSuccessEvent {

    private final String phoneNumber;
    private final String message;


}
