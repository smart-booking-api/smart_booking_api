package com.smart.booking.common.cipher;

import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import java.util.Objects;
import lombok.SneakyThrows;

public class SecureString implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String MASK = "**MASKED**";

    private final String encryptedText;

    private SecureString(String encryptedText) {
        this.encryptedText = encryptedText;
    }

    @SneakyThrows
    public static SecureString of(String raw) {
        return new SecureString(AES256Context.encrypt(raw));
    }

    public static SecureString wrap(String encrypted) {
        return new SecureString(encrypted);
    }

    public static SecureString wrapOrNull(String encrypted) {
        return encrypted != null ? new SecureString(encrypted) : null;
    }

    public String decode() throws Exception {
        return AES256Context.decrypt(encryptedText);
    }

    public String unwrap() {
        return encryptedText;
    }

    @JsonValue
    public String serialize() {
        return encryptedText;
    }

    public String masking() throws Exception {
        return decode().isEmpty() ? "" : MASK;
    }

    @Override
    public String toString() {
        return encryptedText;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SecureString other)) {
            return false;
        }
        return Objects.equals(this.encryptedText, other.encryptedText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(encryptedText);
    }
}
