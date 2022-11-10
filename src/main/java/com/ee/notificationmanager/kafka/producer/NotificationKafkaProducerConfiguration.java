package com.ee.notificationmanager.kafka.producer;

import com.ee.notificationmanager.dto.KafkaKey;
import com.ee.notificationmanager.dto.Order;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@ConfigurationProperties(prefix = "spring.kafka.archive.event-producer")
public class NotificationKafkaProducerConfiguration extends KafkaProducerConfiguration<KafkaKey, Order> {
    
    public NotificationKafkaProducerConfiguration(KafkaProperties kafkaProperties) {
        super(kafkaProperties);
    }

    @Bean
    public ProducerFactory<KafkaKey, Order> archiveEventKafkaProducerFactory() {
        return super.kafkaProducerFactory();
    }

    @Bean
    public KafkaTemplate<KafkaKey, Order> archiveEventKafkaTemplate(
            ProducerFactory<KafkaKey, Order> archiveEventKafkaProducerFactory) {

        return super.kafkaTemplate(archiveEventKafkaProducerFactory);
    }

}