package com.order.ecommerce.integration.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtil {
    public static final ObjectMapper objectmapper = new ObjectMapper();

    public static String asJsonString(final Object obj) {
        try {
            return objectmapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
