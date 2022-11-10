package com.ee.notificationmanager.service.processor;

import com.ee.notificationmanager.dto.KafkaKey;
import com.ee.notificationmanager.dto.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageProcessor {

    private final ObjectMapper jsonMapper;

    public KeyValue<KafkaKey, Order> process(KafkaKey kafkaKey, Order payload) {
        return new KeyValue<>(kafkaKey, payload);
    }

}