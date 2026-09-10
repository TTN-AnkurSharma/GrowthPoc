package com.growthpoc.solid.payment;

public class UPIPayment implements PaymentMethod{

    @Override
    public boolean pay(double amount) {
        System.out.println("Payment using card");
        return true;
    }
}
