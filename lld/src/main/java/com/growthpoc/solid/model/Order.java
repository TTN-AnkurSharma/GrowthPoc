package com.growthpoc.solid.model;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Order {

    private final String orderId;
    private final String customerEmail;
    private final List<OrderItem> items;

    public double getSubtotal() {
        return items.stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
    }
}
