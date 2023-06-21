package com.waa.backend.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StringOrNumberToIntegerDeserializer extends JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Object value = jsonParser.getCodec().readValue(jsonParser, Object.class);

        if (value instanceof String stringValue) {
            if (stringValue.isEmpty()) {
                return null;
            }
            try {
                return Integer.parseInt(stringValue);
            } catch (NumberFormatException e) {
                // Handle the case when the string is not a valid number
                throw new IllegalArgumentException("Invalid integer value: " + stringValue);
            }
        } else if (value instanceof Number) {
            return ((Number) value).intValue();
        } else {
            // Handle the case when the value is neither a string nor a number
            throw new IllegalArgumentException("Invalid value type: " + value.getClass().getSimpleName());
        }
    }
}
