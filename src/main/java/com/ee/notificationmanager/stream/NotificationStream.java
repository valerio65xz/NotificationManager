package com.ee.notificationmanager.stream;

import com.ee.notificationmanager.dto.KafkaKey;
import com.ee.notificationmanager.dto.Order;
import com.ee.notificationmanager.kafka.KafkaTopics;
import com.ee.notificationmanager.service.processor.MessageProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

/**
 * Stream that takes messages from notifications-queue,
 * map them to the MessageProcessor and then send them
 * to the notifications-queue
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationStream {

    private final KafkaTopics kafkaTopics;
    private final MessageProcessor messageProcessor;

    @Autowired
    public void kStream(StreamsBuilder streamsBuilder) {
        streamsBuilder
                .stream(kafkaTopics.getNotifications(), buildConsumeSerdeConfig())
                .map(messageProcessor::process)
                .to(kafkaTopics.getNotifications(), buildProduceSerdeConfig());
    }

    /**
     * Configure SerDes for consumed messages
     */
    private Consumed<KafkaKey, Order> buildConsumeSerdeConfig() {
        return Consumed.with(
                new JsonSerde<>(KafkaKey.class),
                new JsonSerde<>(Order.class)
        );
    }

    /**
     * Configure SerDes for produced messages
     */
    private Produced<KafkaKey, Order> buildProduceSerdeConfig() {
        return Produced.with(
                new JsonSerde<>(KafkaKey.class),
                new JsonSerde<>(Order.class)
        );
    }

}
