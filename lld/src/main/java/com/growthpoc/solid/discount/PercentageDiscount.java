package com.growthpoc.solid.discount;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PercentageDiscount implements DiscountStrategy {

    private final double percentage;

    @Override
    public double calculateDiscount(double amount) {

        return amount * percentage / 100;
    }
}
