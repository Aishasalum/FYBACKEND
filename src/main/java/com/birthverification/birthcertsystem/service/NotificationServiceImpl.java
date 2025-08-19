//package com.birthverification.birthcertsystem.service;
//
//import com.birthverification.birthcertsystem.enums.NotificationStatus;
//import com.birthverification.birthcertsystem.model.Notification;
//import com.birthverification.birthcertsystem.model.User;
//import com.birthverification.birthcertsystem.repository.NotificationRepository;
//import com.birthverification.birthcertsystem.servisecertificate.NotificationService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class NotificationServiceImpl implements NotificationService {
//
//    private final NotificationRepository notificationRepository;
//
//    public NotificationServiceImpl(NotificationRepository notificationRepository) {
//        this.notificationRepository = notificationRepository;
//    }
//
//    @Override
//    @Transactional
//    public Notification sendNotification(User user, String message, com.birthverification.birthcertsystem.enums.NotificationType type, Long relatedRequestId) {
//        Notification notification = new Notification();
//        notification.setUser(user);
//        notification.setMessage(message);
//        notification.setType(type);
//        notification.setStatus(NotificationStatus.UNREAD);
//        notification.setRelatedRequestId(relatedRequestId);
//        notification.setCreatedAt(LocalDateTime.now());
//
//        return notificationRepository.save(notification);
//    }
//
//    @Override
//    public List<Notification> getAllNotificationsForUser(User user) {
//        return notificationRepository.findByUserOrderByCreatedAtDesc(user);
//    }
//
//    @Override
//    public List<Notification> getUnreadNotificationsForUser(User user) {
//        return notificationRepository.findByUserAndStatusOrderByCreatedAtDesc(user, NotificationStatus.UNREAD);
//    }
//
//    @Override
//    @Transactional
//    public void markAsRead(Long notificationId) {
//        Notification notification = notificationRepository.findById(notificationId)
//                .orElseThrow(() -> new RuntimeException("Notification not found with ID: " + notificationId));
//        notification.setStatus(NotificationStatus.READ);
//        notification.setReadAt(LocalDateTime.now());
//        notificationRepository.save(notification);
//    }
//}




//mpyaaa read nad not read
package com.birthverification.birthcertsystem.service;

import com.birthverification.birthcertsystem.enums.NotificationStatus;
import com.birthverification.birthcertsystem.model.Notification;
import com.birthverification.birthcertsystem.model.User;
import com.birthverification.birthcertsystem.repository.NotificationRepository;
import com.birthverification.birthcertsystem.servisecertificate.NotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    @Transactional
    public Notification sendNotification(User user, String message, com.birthverification.birthcertsystem.enums.NotificationType type, Long relatedRequestId) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setType(type);
        notification.setStatus(NotificationStatus.UNREAD);
        notification.setRelatedRequestId(relatedRequestId);
        notification.setCreatedAt(LocalDateTime.now());

        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotificationsForUser(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public List<Notification> getUnreadNotificationsForUser(Long userId) {
        return notificationRepository.findByUserIdAndStatusOrderByCreatedAtDesc(userId, NotificationStatus.UNREAD);
    }

    @Override
    @Transactional
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found with ID: " + notificationId));
        notification.setStatus(NotificationStatus.READ);
        notification.setReadAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    @Override
    @Transactional
    public void markAsUnread(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found with ID: " + notificationId));
        notification.setStatus(NotificationStatus.UNREAD);
        notification.setReadAt(null);
        notificationRepository.save(notification);
    }
}
