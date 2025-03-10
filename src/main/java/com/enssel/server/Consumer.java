package com.enssel.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "metric-test", groupId = "metric-group")
    public void consume(String message) {
        try{
            Metric metric = objectMapper.readValue(message,Metric.class);
            log.info(metric.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
