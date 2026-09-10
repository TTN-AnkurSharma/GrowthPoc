package com.growthpoc.solid.checkout;

import com.growthpoc.solid.discount.DiscountStrategy;
import com.growthpoc.solid.model.Order;
import com.growthpoc.solid.notification.NotificationService;
import com.growthpoc.solid.payment.PaymentMethod;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class CheckoutService {

    PaymentMethod paymentMethod;
    DiscountStrategy discountStrategy;
    NotificationService notificationService;

    public void checkout(Order order) {

        double subtotal = order.getSubtotal();
        double discount = discountStrategy.calculateDiscount(subtotal);
        double finalAmount = subtotal - discount;

        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Subtotal: ₹" + subtotal);
        System.out.println("Discount: ₹" + discount);
        System.out.println("Final Amount: ₹" + finalAmount);

        boolean paymentSuccessful = paymentMethod.pay(finalAmount);
        if (!paymentSuccessful) {
            throw new RuntimeException("Payment failed");
        }

        notificationService.sendOrderConfirmation(order.getCustomerEmail(), order.getOrderId(), finalAmount);
    }
}
