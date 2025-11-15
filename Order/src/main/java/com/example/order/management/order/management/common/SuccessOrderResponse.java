package com.example.order.management.order.management.common;

import com.example.order.management.order.management.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;

@Getter
public class SuccessOrderResponse implements OrderResponse {
    @JsonUnwrapped
    private final OrderDTO order;
    public SuccessOrderResponse(OrderDTO order) {
        this.order = order;
    }
}
