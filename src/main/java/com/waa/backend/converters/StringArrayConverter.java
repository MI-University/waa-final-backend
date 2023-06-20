package com.waa.backend.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

//@Converter
public class StringArrayConverter implements AttributeConverter<String[], String> {

    private static final String ARRAY_SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(String[] attribute) {
        if (attribute == null || attribute.length == 0) {
            return null;
        }
        return String.join(ARRAY_SEPARATOR, attribute);
    }

    @Override
    public String[] convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        return dbData.split(ARRAY_SEPARATOR);
    }
}
