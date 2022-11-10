package com.ee.notificationmanager.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventName {

    ORDER_CREATED("OrderCreated"),
    ORDER_SHIPPED("OrderShipped");

    private final String value;

    @Override
    public String toString() {
        return this.value;
    }

}
