package com.github.revenuemonster.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.util.Map;

public class JSON {

    public static String stringify(Map<String, Object> object) throws JsonProcessingException {
        ObjectMapper myObjectMapper = new ObjectMapper();
        myObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        myObjectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        ObjectWriter ow = myObjectMapper.writer();
        return ow.writeValueAsString(object);
    }

    public static <T> T parse(String response, Class<T> clazz) throws IOException {
        ObjectMapper myObjectMapper = new ObjectMapper();
        return myObjectMapper.readValue(response, clazz);
    }

}
