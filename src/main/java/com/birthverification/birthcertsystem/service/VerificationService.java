//package com.birthverification.birthcertsystem.service;
//
//import com.birthverification.birthcertsystem.model.Notification;
//import com.birthverification.birthcertsystem.model.NotificationStatus;
//import com.birthverification.birthcertsystem.model.Verification;
//import com.birthverification.birthcertsystem.model.VerificationStatus;
//import com.birthverification.birthcertsystem.repository.VerificationRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class VerificationService {
//
//    @Autowired
//    private VerificationRepository verificationRepository;
//
//    @Autowired
//    private NotificationService notificationService;
//
//    public Verification saveVerification(Verification verification) {
//        return verificationRepository.save(verification);
//    }
//
//    public List<Verification> getAllVerifications() {
//        return verificationRepository.findAll();
//    }
//
//    public Verification getVerificationById(Long id) {
//        return verificationRepository.findById(id).orElse(null);
//    }
//
//    public void deleteVerification(Long id) {
//        verificationRepository.deleteById(id);
//    }
//
//    // ✅ NEW: Approve and send notification
//    public Verification approveVerification(Long id) {
//        Verification verification = verificationRepository.findById(id).orElseThrow();
//
//        verification.setStatus(VerificationStatus.APPROVED);
//        verificationRepository.save(verification);
//
//        // Create notification using setters instead of builder
//        Notification notification = new Notification();
//        notification.setUser(verification.getUser()); // Ensure Verification has getUser()
//        notification.setChannel("EMAIL"); // or "SMS"
//        notification.setMessage("Your certificate is approved");
//        notification.setSentAt(LocalDateTime.now());
//        notification.setStatus(NotificationStatus.SENT);
//
//        notificationService.sendNotification(notification);
//
//        return verification;
//    }
//}
