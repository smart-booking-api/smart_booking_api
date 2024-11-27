package com.smart.booking.domain.partner.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChangePartnerPasswordDto {

    private final String password;
    private final String newPassword;


}
