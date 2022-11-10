package com.ee.notificationmanager.service;

import com.ee.notificationmanager.dto.EEOrder;
import com.ee.notificationmanager.dto.EventName;
import com.ee.notificationmanager.dto.KafkaKey;
import com.ee.notificationmanager.producer.NotificationKafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationKafkaProducer notificationKafkaProducer;

    public void write(){
        Integer orderId = 1;
        EventName eventName = EventName.ORDER_CREATED;
        String customerName = "customerName";

        KafkaKey key = new KafkaKey(orderId, eventName);
        EEOrder order = new EEOrder();
        order.setOrderId(orderId);
        order.setCustomerName(customerName);

        notificationKafkaProducer.sendMessage(key, order, "orders");
    }

}