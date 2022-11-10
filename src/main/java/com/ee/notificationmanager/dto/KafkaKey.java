package com.ee.notificationmanager.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KafkaKey {

    private Integer orderId;
    private EventName eventName;

}
