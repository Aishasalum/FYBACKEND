//package com.birthverification.birthcertsystem.servisecertificate;
//
//import com.birthverification.birthcertsystem.enums.NotificationStatus;
//import com.birthverification.birthcertsystem.model.Notification;
//import com.birthverification.birthcertsystem.model.User;
//
//import java.util.List;
//
//public interface NotificationService {
//
//    Notification sendNotification(User user, String message, com.birthverification.birthcertsystem.enums.NotificationType type, Long relatedRequestId);
//
//    List<Notification> getAllNotificationsForUser(User user);
//
//    List<Notification> getUnreadNotificationsForUser(User user);
//
//    void markAsRead(Long notificationId);
//
//    void markAsUnread(Long notificationId);
//}


//NEWWWW


package com.birthverification.birthcertsystem.servisecertificate;

import com.birthverification.birthcertsystem.enums.NotificationType;
import com.birthverification.birthcertsystem.model.Notification;
import com.birthverification.birthcertsystem.model.User;

import java.util.List;

public interface NotificationService {

    Notification sendNotification(User user, String message, NotificationType type, Long relatedRequestId);

    List<Notification> getAllNotificationsForUser(Long userId);

    List<Notification> getUnreadNotificationsForUser(Long userId);

    void markAsRead(Long notificationId);

    void markAsUnread(Long notificationId);
}
