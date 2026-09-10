package com.growthpoc.solid.payment;

public class CardPayment implements PaymentMethod {

    @Override
    public boolean pay(double amount) {

        System.out.println(
                "Processing card payment of ₹" + amount);
        System.out.println("Card payment successful");
        return true;
    }
}
