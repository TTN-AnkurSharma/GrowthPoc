package com.growthpoc.solid.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderItem {

    private final String productName;
    private final double price;
    private final int quantity;

    public double getTotalPrice() {
        return price * quantity;
    }
}
