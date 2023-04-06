package com.kafkaexample.broker.controller;

import com.kafkaexample.broker.dto.MessageDto;
import com.kafkaexample.broker.producer.TopicProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/kafka")
public class KafkaController {
    private final TopicProducer topicProducer;
    @PostMapping(value = "/send")
    public void send(@RequestBody MessageDto producedString){
        topicProducer.send(producedString.producedString);
    }
}
