package com.kafkaexample.broker.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@Component
public class SerializerHelper {
    public static ObjectMapper mapper;
    public static ObjectMapper getInstance(){
        if(mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;

    }

}
