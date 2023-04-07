package com.kafkaexample.broker.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaexample.broker.dto.MessageDto;
import com.kafkaexample.broker.dto.Product;
import com.kafkaexample.broker.util.SerializerHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Component
public class KafkaConsumer {
    private final SerializerHelper serializerHelper;
    @KafkaListener(topics = "my-topic", groupId = "group_id")
    public void consume(String message)
    {
        ObjectMapper objectMapper = serializerHelper.getInstance();
        MessageDto comingPrice = null;
        try {
            comingPrice = objectMapper.readValue(message, MessageDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("message = " + comingPrice.price + "at time : " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
    }
}
