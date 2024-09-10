package com.rosedine.rosedine.controller;

import com.rosedine.rosedine.dto.NotificationFood;
import com.rosedine.rosedine.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController extends BaseController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/get-notifications")
    public ResponseEntity<List<NotificationFood>> getNotifications(@RequestParam int userId) {
        try {
            List<NotificationFood> notifications = notificationService.getNotificationFoods(userId);
            return handleSuccess(notifications);
        } catch (Exception e) {
            return handleException(e);
        }
    }
}