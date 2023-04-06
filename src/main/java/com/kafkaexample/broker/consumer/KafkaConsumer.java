package com.kafkaexample.broker.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaexample.broker.dto.Product;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "my-topic",groupId = "group_id")
    public void consume(String message)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = null;
        try {
            product = objectMapper.readValue(message, Product.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("message = " + product.price);
    }
}
