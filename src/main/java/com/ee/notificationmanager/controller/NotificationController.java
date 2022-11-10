package com.ee.notificationmanager.controller;

import com.ee.notificationmanager.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Void> createOrder(){
        notificationService.write();
        return ResponseEntity.accepted().build();
    }

}