package com.growthpoc.solid.notification;

public interface NotificationSender {

    void send(String to, String subject, String message);
}
