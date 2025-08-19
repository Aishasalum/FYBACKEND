//package com.birthverification.birthcertsystem.controller;
//
//import com.birthverification.birthcertsystem.model.Notification;
//import com.birthverification.birthcertsystem.model.User;
//import com.birthverification.birthcertsystem.servisecertificate.NotificationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/notifications")
//public class NotificationController {
//
//    private final NotificationService notificationService;
//
//    @Autowired
//    public NotificationController(NotificationService notificationService) {
//        this.notificationService = notificationService;
//    }
//
//    // Pata notifications zote za user
//    @GetMapping("/user/{userId}")
//    public List<Notification> getAllNotifications(@PathVariable Long userId) {
//        User user = new User();
//        user.setId(userId); // Assuming you just set ID to fetch notifications
//        return notificationService.getAllNotificationsForUser(user);
//    }
//
//    // Pata notifications zisomoshiwa bado
//    @GetMapping("/user/{userId}/unread")
//    public List<Notification> getUnreadNotifications(@PathVariable Long userId) {
//        User user = new User();
//        user.setId(userId);
//        return notificationService.getUnreadNotificationsForUser(user);
//    }
//
//    // Mark notification kama read
//    @PostMapping("/{notificationId}/read")
//    public String markAsRead(@PathVariable Long notificationId) {
//        notificationService.markAsRead(notificationId);
//        return "Notification marked as read";
//    }
//
//    // Optional: Send notification (Admin or Verifier use)
//    @PostMapping("/send")
//    public Notification sendNotification(
//            @RequestParam Long userId,
//            @RequestParam String message,
//            @RequestParam String type, // "POPUP" or "EMAIL"
//            @RequestParam(required = false) Long relatedRequestId
//    ) {
//        User user = new User();
//        user.setId(userId);
//
//        // Convert String to enum
//        com.birthverification.birthcertsystem.enums.NotificationType notificationType =
//                com.birthverification.birthcertsystem.enums.NotificationType.valueOf(type.toUpperCase());
//
//        return notificationService.sendNotification(user, message, notificationType, relatedRequestId);
//    }
//}





//mpyaaaa read and not read
package com.birthverification.birthcertsystem.controller;

import com.birthverification.birthcertsystem.enums.NotificationType;
import com.birthverification.birthcertsystem.model.Notification;
import com.birthverification.birthcertsystem.model.User;
import com.birthverification.birthcertsystem.model.CertificateVerificationRequest;
import com.birthverification.birthcertsystem.servisecertificate.NotificationService;
import com.birthverification.birthcertsystem.servisecertificate.CertificateVerificationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    private final NotificationService notificationService;
    private final CertificateVerificationRequestService certificateRequestService;

    @Autowired
    public NotificationController(NotificationService notificationService,
                                  CertificateVerificationRequestService certificateRequestService) {
        this.notificationService = notificationService;
        this.certificateRequestService = certificateRequestService;
    }

    // ===== Get all notifications for a user =====
    @GetMapping("/user/{userId}")
    public List<Notification> getAllNotifications(@PathVariable Long userId) {
        return notificationService.getAllNotificationsForUser(userId);
    }

    // ===== Get only unread notifications =====
    @GetMapping("/user/{userId}/unread")
    public List<Notification> getUnreadNotifications(@PathVariable Long userId) {
        return notificationService.getUnreadNotificationsForUser(userId);
    }

    // ===== Mark as Read =====
    @PutMapping("/mark-read/{notificationId}")
    public ResponseEntity<Void> markAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.noContent().build();
    }

    // ===== Mark as Unread =====
    @PutMapping("/mark-unread/{notificationId}")
    public ResponseEntity<Void> markAsUnread(@PathVariable Long notificationId) {
        notificationService.markAsUnread(notificationId);
        return ResponseEntity.noContent().build();
    }

    // ===== Send notification manually =====
    @PostMapping("/send")
    public Notification sendNotification(
            @RequestParam Long userId,
            @RequestParam String message,
            @RequestParam String type, // "POPUP" or "EMAIL"
            @RequestParam(required = false) Long relatedRequestId
    ) {
        User user = new User();
        user.setId(userId);

        NotificationType notificationType = NotificationType.valueOf(type.toUpperCase());
        return notificationService.sendNotification(user, message, notificationType, relatedRequestId);
    }

    // ===== Get request details linked to a notification =====
    @GetMapping("/request-details/{relatedRequestId}")
    public ResponseEntity<CertificateVerificationRequest> getRequestDetails(@PathVariable Long relatedRequestId) {
        CertificateVerificationRequest request = certificateRequestService
                .getRequestById(relatedRequestId)
                .orElseThrow(() -> new RuntimeException("Request not found with ID: " + relatedRequestId));

        return ResponseEntity.ok(request);
    }
}
