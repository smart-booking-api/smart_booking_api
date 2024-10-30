package com.smart.booking.domain.partner.dto;

import com.smart.booking.domain.partner.value_object.BankAccount;
import com.smart.booking.domain.partner.value_object.CompanyManager;
import com.smart.booking.domain.partner.value_object.CompanyRepresentative;

public record UpsertPartnerCompanyDto(
    String name,
    String address,
    String fax,
    String businessType,
    String businessCategory,
    String etaxEmail,
    BankAccount bankAccount,
    CompanyRepresentative representative,
    CompanyManager manager,
    String memo
) {

}
