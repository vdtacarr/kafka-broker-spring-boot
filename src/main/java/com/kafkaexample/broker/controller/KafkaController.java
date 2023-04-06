package com.kafkaexample.broker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaexample.broker.dto.MessageDto;
import com.kafkaexample.broker.dto.Product;
import com.kafkaexample.broker.producer.TopicProducer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/kafka")
public class KafkaController {
    private final TopicProducer topicProducer;
    @PostMapping(value = "/send")
    public void send(@RequestBody MessageDto producedString){
        var mapper = new ObjectMapper();
        String jsonString="";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        mapper.setDateFormat(df);

        var product = new Product();
        product.name = "Computer";
        product.price = 10000L;
        try {
             jsonString = mapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        topicProducer.send(jsonString);
    }
}
