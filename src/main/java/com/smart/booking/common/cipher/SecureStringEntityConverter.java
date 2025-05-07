package com.smart.booking.common.cipher;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SecureStringEntityConverter implements AttributeConverter<SecureString, String> {

    @Override
    public String convertToDatabaseColumn(SecureString attribute) {
        return attribute != null ? attribute.unwrap() : null;
    }

    @Override
    public SecureString convertToEntityAttribute(String dbData) {
        return SecureString.wrapOrNull(dbData);
    }
}