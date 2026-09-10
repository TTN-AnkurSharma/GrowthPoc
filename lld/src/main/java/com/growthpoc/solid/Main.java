package com.growthpoc.solid;

import com.growthpoc.solid.checkout.CheckoutService;
import com.growthpoc.solid.discount.DiscountStrategy;
import com.growthpoc.solid.discount.PercentageDiscount;
import com.growthpoc.solid.model.Order;
import com.growthpoc.solid.model.OrderItem;
import com.growthpoc.solid.notification.EmailSender;
import com.growthpoc.solid.notification.NotificationSender;
import com.growthpoc.solid.notification.NotificationService;
import com.growthpoc.solid.payment.CardPayment;
import com.growthpoc.solid.payment.PaymentMethod;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        OrderItem laptop = new OrderItem("Laptop", 80000, 1);

        OrderItem mouse = new OrderItem("Mouse", 2000, 2);

        Order order = new Order("ORD-101", "customer@example.com", List.of(laptop, mouse));

        PaymentMethod paymentMethod = new CardPayment();
        /*If in future we need to change it to UPI payment no need to change checkout service
        it is open for extension and closed for modification */

        DiscountStrategy discountStrategy = new PercentageDiscount(10);

        NotificationSender notificationSender = new EmailSender();

        NotificationService notificationService = new NotificationService(notificationSender);

        CheckoutService checkoutService = new CheckoutService(paymentMethod, discountStrategy, notificationService);

        checkoutService.checkout(order);
    }
}