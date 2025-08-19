package com.birthverification.birthcertsystem.repository;

import com.birthverification.birthcertsystem.model.Notification;
import com.birthverification.birthcertsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Pata notifications zote za user fulani, order kwa latest kwanza
    List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);

    // Pata notifications zisomoshiwa bado
    List<Notification> findByUserIdAndStatusOrderByCreatedAtDesc(Long userId, com.birthverification.birthcertsystem.enums.NotificationStatus status);

}
