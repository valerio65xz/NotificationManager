package com.ee.notificationmanager.kafka.partitioner;

import com.ee.notificationmanager.dto.KafkaKey;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;

public class ArchiveEventKafkaPartitioner extends DefaultPartitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        KafkaKey kafkaKey = (KafkaKey) key;
        byte[] keyBytesx = new byte[0];
        keyBytes[0] = kafkaKey.getOrderId().byteValue();
        return super.partition(topic, kafkaKey.getOrderId(), keyBytesx, value, valueBytes, cluster);
    }
    
}