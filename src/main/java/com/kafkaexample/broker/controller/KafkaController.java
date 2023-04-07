package com.kafkaexample.broker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafkaexample.broker.dto.MessageDto;
import com.kafkaexample.broker.dto.Product;
import com.kafkaexample.broker.producer.TopicProducer;
import com.kafkaexample.broker.util.SerializerHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/kafka")
public class KafkaController {
    private final TopicProducer topicProducer;
    private final SerializerHelper serializerHelper;
    @PostMapping(value = "/send")
    public void send(@RequestBody MessageDto producedString){
        var mapper = serializerHelper.getInstance();
        String jsonString="";

        try {
             jsonString = mapper.writeValueAsString(producedString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        topicProducer.send(jsonString);
    }
}
