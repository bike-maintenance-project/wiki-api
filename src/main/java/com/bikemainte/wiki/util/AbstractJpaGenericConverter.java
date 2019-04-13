package com.bikemainte.wiki.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.io.IOException;

/**
 * @author hongyu
 * @date 4:08 PM 13/4/2019
 */
@Slf4j
public abstract class AbstractJpaGenericConverter<T> implements AttributeConverter<T, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(T o) {
        if (o == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("Json转换错误" + e);
            return null;
        }
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return objectMapper.readValue(dbData, getClazz());
        } catch (IOException ex) {
            log.error("Unexpected IOEx decoding json from database: " + dbData);
            return null;
        }
    }

    protected abstract Class<T> getClazz();
}
