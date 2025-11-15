package com.example.order.management.order.management.common;

import com.example.order.management.order.management.dto.OrderDTO;
import lombok.Getter;

@Getter
public class SuccessOrderResponse implements OrderResponse {
    private final OrderDTO order;
    public SuccessOrderResponse(OrderDTO order) {
        this.order = order;
    }
}
