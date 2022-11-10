package com.ee.notificationmanager.producer;

import com.ee.notificationmanager.dto.KafkaKey;
import com.ee.notificationmanager.dto.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationKafkaProducer extends KafkaProducer<KafkaKey, Order> {

    public NotificationKafkaProducer(KafkaTemplate<KafkaKey, Order> kafkaTemplate) {
        super(kafkaTemplate);
    }

    public void sendMessage(KafkaKey kafkaKey, Order payload, String topic) {
        super.sendMessage(kafkaKey, payload, topic);
    }

}
