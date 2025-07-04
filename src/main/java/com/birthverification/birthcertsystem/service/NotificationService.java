package com.birthverification.birthcertsystem.service;

import com.birthverification.birthcertsystem.model.Notification;
import com.birthverification.birthcertsystem.model.NotificationStatus;
import com.birthverification.birthcertsystem.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    /** Hifadhi na (baadaye) tumia SMS/Email provider */
    public Notification sendNotification(Notification notification) {
        // ▶️ sehemu ya kutuma SMS/Email unaweza ku-integrate Twilio/SendGrid hapa.
        notification.setSentAt(LocalDateTime.now());
        notification.setStatus(NotificationStatus.SENT);   // au FAILED kulingana na matokeo
        return repository.save(notification);
    }

    public List<Notification> getAll() {
        return repository.findAll();
    }

    public List<Notification> getByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public Notification getOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
