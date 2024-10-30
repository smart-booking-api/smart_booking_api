package com.smart.booking.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 객체를 문자로 변환
     * @param obj
     * @return
     */
    public static String convertToString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON -> String 변환 오류", e);
        }
    }

    /**
     * 문자를 객체로 변환
     * @param json
     * @param object
     * @return
     * @param <T>
     */
    public static <T> T convertToObject(String json, Class<T> object) {
        try {
            return objectMapper.readValue(json, object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("String -> JSON 변환 오류", e);
        }
    }
}