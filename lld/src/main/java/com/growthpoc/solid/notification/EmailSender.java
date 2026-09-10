package com.growthpoc.solid.notification;

public class EmailSender implements NotificationSender {

    @Override
    public void send(String to, String subject, String message) {

        System.out.println("Sending email");
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }
}