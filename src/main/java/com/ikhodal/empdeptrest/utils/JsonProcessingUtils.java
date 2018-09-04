package com.ikhodal.empdeptrest.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class JsonProcessingUtils
{
    public String convertObjectToJSONString(Object object) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(object);
        return jsonString;
    }
}
