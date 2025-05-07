package com.smart.booking.common.configs;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.smart.booking.common.cipher.AES256Context;
import com.smart.booking.common.cipher.SecureString;
import java.io.IOException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class CipherConfig {

    public CipherConfig(
        @Value("${secret.key}") String key,
        @Value("${secret.iv}") String iv
    ) {
        AES256Context.setKey(key, iv);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer objectMapper() {
        return builder -> {
            builder.serializers(new SecureStringSerializer());
            builder.deserializers(new SecureStringDeserializer());
        };
    }

    @Bean
    public SecureStringConverter secureStringConverter() {
        return new SecureStringConverter();
    }

    public static class SecureStringConverter implements Converter<String, SecureString> {

        @Override
        public SecureString convert(@NonNull String source) {
            try {
                return SecureString.of(source);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class SecureStringSerializer extends StdSerializer<SecureString> {

        public SecureStringSerializer() {
            super(SecureString.class);
        }

        @Override
        public void serialize(@NonNull SecureString value, @NonNull JsonGenerator gen, SerializerProvider provider) throws IOException {
            try {
                gen.writeString(value.decode());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class SecureStringDeserializer extends StdDeserializer<SecureString> {

        public SecureStringDeserializer() {
            super(SecureString.class);
        }

        @Override
        public SecureString deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            try {
                return SecureString.of(p.getValueAsString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
