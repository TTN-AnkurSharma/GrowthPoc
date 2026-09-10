package com.growthpoc.solid.notification;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotificationService {

    private final NotificationSender notificationSender;

    public void sendOrderConfirmation(String email, String orderId, double amount) {

        String subject = "Order Confirmation";
        String message =
                "Your order " + orderId +
                        " has been successfully placed." +
                        "\nAmount: ₹" + amount;

        notificationSender.send(email, subject, message);
    }
}
