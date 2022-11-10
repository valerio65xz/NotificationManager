package com.ee.notificationmanager.producer;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
@RequiredArgsConstructor
public abstract class KafkaProducer<K, V> {

    protected final KafkaTemplate<K, V> kafkaTemplate;

    public void sendMessage(K kafkaKey, V payload, String topic) {
        Message<V> message = MessageBuilder.withPayload(payload)
                .setHeader(KafkaHeaders.MESSAGE_KEY, kafkaKey)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();

        kafkaTemplate.send(message).addCallback(
                this::successHandler,
                this::failureHandler
        );
    }

    protected void successHandler(@NonNull SendResult<K, V> result) {
        log.debug(
                "Message with key '{}' has been successfully sent to the partition '{}' of the topic '{}'",
                result.getProducerRecord().key(),
                result.getRecordMetadata().partition(),
                result.getProducerRecord().topic()
        );
    }

    protected void failureHandler(Throwable throwable) {
        log.error(throwable.getMessage(), throwable);
    }

}
