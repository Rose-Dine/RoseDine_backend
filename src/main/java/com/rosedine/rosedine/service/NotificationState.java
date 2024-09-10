package com.rosedine.rosedine.service;
import com.rosedine.rosedine.dto.NotificationFood;
import java.util.List;

public interface NotificationState {
    void sendNotification(List<NotificationFood> foods);
}

class EnabledState implements NotificationState {
    @Override
    public void sendNotification(List<NotificationFood> foods) {
        System.out.println("Sending notification for " + foods.size() + " food items");
    }
}

class DisabledState implements NotificationState {
    @Override
    public void sendNotification(List<NotificationFood> foods) {
        System.out.println("Notifications are disabled");
    }
}

class DoNotDisturbState implements NotificationState {
    @Override
    public void sendNotification(List<NotificationFood> foods) {
        System.out.println("Queuing notification for " + foods.size() + " food items for later");
    }
}