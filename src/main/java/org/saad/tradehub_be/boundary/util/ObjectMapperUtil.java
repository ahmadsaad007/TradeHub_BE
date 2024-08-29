package org.saad.tradehub_be.boundary.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ObjectMapperUtil {

    private final ObjectMapper objectMapper;

    /**
     * Maps the request body to the specified class type.
     *
     * @param requestBody The request body as a String.
     * @param clazz       The class type to which the request body should be mapped.
     * @param <T>         The type of the class.
     * @return The mapped object of the specified class type.
     * @throws Exception If there is a mapping error.
     */
    public <T> T mapRequestBody(String requestBody, Class<T> clazz) throws Exception {
        return objectMapper.readValue(requestBody, clazz);
    }
}
