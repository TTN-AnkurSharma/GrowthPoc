package com.growthpoc.solid.discount;

public class NoDiscount implements DiscountStrategy {

    @Override
    public double calculateDiscount(double amount) {
        return 0;
    }
}