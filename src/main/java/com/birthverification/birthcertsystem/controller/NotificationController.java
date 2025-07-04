package com.birthverification.birthcertsystem.controller;

import com.birthverification.birthcertsystem.model.Notification;
import com.birthverification.birthcertsystem.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationService service;

    /** Tuma notification mpya */
    @PostMapping
    public Notification create(@RequestBody Notification notification) {
        return service.sendNotification(notification);
    }

    /** Orodha ya notifications zote */
    @GetMapping
    public List<Notification> getAll() {
        return service.getAll();
    }

    /** Notifications za mtumiaji fulani */
    @GetMapping("/user/{userId}")
    public List<Notification> getByUser(@PathVariable Long userId) {
        return service.getByUser(userId);
    }

    /** Notification moja kwa ID */
    @GetMapping("/{id}")
    public Notification getOne(@PathVariable Long id) {
        return service.getOne(id);
    }

    /** Futa notification */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
