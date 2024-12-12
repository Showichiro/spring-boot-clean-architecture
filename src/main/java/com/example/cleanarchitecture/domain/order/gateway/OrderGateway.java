package com.example.cleanarchitecture.domain.order.gateway;

import com.example.cleanarchitecture.core.domain.IGateway;
import com.example.cleanarchitecture.domain.order.model.Order;

public interface OrderGateway extends IGateway<Order, Long> {
}
