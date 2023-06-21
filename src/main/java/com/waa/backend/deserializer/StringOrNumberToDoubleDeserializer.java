package com.waa.backend.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StringOrNumberToDoubleDeserializer extends JsonDeserializer<Double> {
    @Override
    public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Object value = jsonParser.getCodec().readValue(jsonParser, Object.class);

        if (value instanceof String stringValue) {
            if (stringValue.isEmpty()) {
                return null;
            }
            try {
                return Double.parseDouble(stringValue);
            } catch (NumberFormatException e) {
                // Handle the case when the string is not a valid number
                throw new IllegalArgumentException("Invalid double value: " + stringValue);
            }
        } else if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else {
            // Handle the case when the value is neither a string nor a number
            throw new IllegalArgumentException("Invalid value type: " + value.getClass().getSimpleName());
        }
    }
}
