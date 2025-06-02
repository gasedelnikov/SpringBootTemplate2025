package ru.gri.core.dao.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Timestamp;
import java.time.Instant;

@Converter
public class TimeStampToLongConverter implements AttributeConverter<Long, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(Long meta) {
        return (meta == null)
                ? null
                : Timestamp.from(Instant.ofEpochMilli(meta));
    }

    @Override
    public Long convertToEntityAttribute(Timestamp dbData) {
        return (dbData == null)
                ? null
                : dbData.getTime();
    }
}