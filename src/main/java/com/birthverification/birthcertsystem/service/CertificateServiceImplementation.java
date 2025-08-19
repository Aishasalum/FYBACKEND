//package com.birthverification.birthcertsystem.service;
//
//import com.birthverification.birthcertsystem.enums.VerificationStatus;
//import com.birthverification.birthcertsystem.model.Certificate;
//import com.birthverification.birthcertsystem.model.User;
//import com.birthverification.birthcertsystem.repository.CertificateRepository;
//import com.birthverification.birthcertsystem.repository.UserRepository;
//import com.birthverification.birthcertsystem.servisecertificate.CertificateService;
//import com.birthverification.birthcertsystem.servisecertificate.FileStorageService;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//public class CertificateServiceImplementation implements CertificateService {
//
//    private final CertificateRepository certificateRepository;
//    private final UserRepository userRepository;
//    private final FileStorageService fileStorageService;
//
//    public CertificateServiceImplementation(CertificateRepository certificateRepository,
//                                            UserRepository userRepository,
//                                            FileStorageService fileStorageService) {
//        this.certificateRepository = certificateRepository;
//        this.userRepository = userRepository;
//        this.fileStorageService = fileStorageService;
//    }
//
//    @Override
//    public Certificate uploadCertificate(Long userId, String certNumber, String certName, MultipartFile file) throws IOException {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        String fileUrl = fileStorageService.storeFile(file);
//
//        Certificate cert = new Certificate();
//        cert.setCertificateNumber(certNumber);
//        cert.setCertificateName(certName);
//        cert.setFileUrl(fileUrl);
//        cert.setVerificationStatus(VerificationStatus.PENDING);
//        cert.setUser(user);
//
//        // Set createdAt handled automatically by @PrePersist, no need to set manually here
//
//        return certificateRepository.save(cert);
//    }
//
//    @Override
//    public Certificate getCertificateById(Long id) {
//        return certificateRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public List<Certificate> getAllCertificates() {
//        return certificateRepository.findAll();
//    }
//
//    @Override
//    public void deleteCertificate(Long id) {
//        certificateRepository.deleteById(id);
//    }
//}




//
//package com.birthverification.birthcertsystem.service;
//
//import com.birthverification.birthcertsystem.enums.VerificationStatus;
//import com.birthverification.birthcertsystem.model.Certificate;
//import com.birthverification.birthcertsystem.model.User;
//import com.birthverification.birthcertsystem.repository.CertificateRepository;
//import com.birthverification.birthcertsystem.repository.UserRepository;
//import com.birthverification.birthcertsystem.servisecertificate.CertificateService;
//import com.birthverification.birthcertsystem.servisecertificate.FileStorageService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//public class CertificateServiceImplementation implements CertificateService {
//
//    private final CertificateRepository certificateRepository;
//    private final UserRepository userRepository;
//    private final FileStorageService fileStorageService;
//
//    public CertificateServiceImplementation(CertificateRepository certificateRepository,
//                                            UserRepository userRepository,
//                                            FileStorageService fileStorageService) {
//        this.certificateRepository = certificateRepository;
//        this.userRepository = userRepository;
//        this.fileStorageService = fileStorageService;
//    }
//
//    @Override
//    public Certificate uploadCertificate(Long userId, String certNumber, String certName, MultipartFile file) throws IOException {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        String fileUrl = fileStorageService.storeFile(file);
//
//        Certificate cert = new Certificate();
//        cert.setCertificateNumber(certNumber);
//        cert.setCertificateName(certName);
//        cert.setFileUrl(fileUrl);
//        cert.setVerificationStatus(VerificationStatus.PENDING);
//        cert.setUser(user);
//
//        // Upload date is set automatically by @PrePersist
//        return certificateRepository.save(cert);
//    }
//
//    @Override
//    public Certificate getCertificateById(Long id) {
//        return certificateRepository.findById(id).orElse(null);
//    }
//
//    // Return certificates not deleted (active)
//    @Override
//    public List<Certificate> getAllActiveCertificates() {
//        return certificateRepository.findByDeletedFalse();
//    }
//
//    // Return certificates deleted (in trash)
//    @Override
//    public List<Certificate> getAllDeletedCertificates() {
//        return certificateRepository.findByDeletedTrue();
//    }
//
//    // Soft delete - mark deleted = true
//    @Override
//    @Transactional
//    public void softDeleteCertificate(Long id) {
//        Certificate cert = certificateRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Certificate not found"));
//        cert.setDeleted(true);
//        certificateRepository.save(cert);
//    }
//
//    // Restore soft deleted certificate (deleted = false)
//    @Override
//    @Transactional
//    public void restoreCertificate(Long id) {
//        Certificate cert = certificateRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Certificate not found"));
//        cert.setDeleted(false);
//        certificateRepository.save(cert);
//    }
//
//    // Hard delete - permanent removal
//    @Override
//    @Transactional
//    public void hardDeleteCertificate(Long id) {
//        Certificate cert = certificateRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Certificate not found"));
//        certificateRepository.delete(cert);
//    }
//}




//mpyaa ya kutuma notification kwa email



package com.birthverification.birthcertsystem.service;

import com.birthverification.birthcertsystem.enums.VerificationStatus;
import com.birthverification.birthcertsystem.model.Certificate;
import com.birthverification.birthcertsystem.model.User;
import com.birthverification.birthcertsystem.repository.CertificateRepository;
import com.birthverification.birthcertsystem.repository.UserRepository;
import com.birthverification.birthcertsystem.servisecertificate.CertificateService;
import com.birthverification.birthcertsystem.servisecertificate.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CertificateServiceImplementation implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    public CertificateServiceImplementation(CertificateRepository certificateRepository,
                                            UserRepository userRepository,
                                            FileStorageService fileStorageService) {
        this.certificateRepository = certificateRepository;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public Certificate uploadCertificate(Long userId, String certNumber, String certName, MultipartFile file) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String fileUrl = fileStorageService.storeFile(file);

        Certificate cert = new Certificate();
        cert.setCertificateNumber(certNumber);
        cert.setCertificateName(certName);
        cert.setFileUrl(fileUrl);
        cert.setVerificationStatus(VerificationStatus.PENDING);
        cert.setUser(user);

        // Upload date is set automatically by @PrePersist in entity
        return certificateRepository.save(cert);
    }

    @Override
    public Certificate getCertificateById(Long id) {
        return certificateRepository.findById(id).orElse(null);
    }

    @Override
    public List<Certificate> getAllActiveCertificates() {
        return certificateRepository.findByDeletedFalse();
    }

    @Override
    public List<Certificate> getAllDeletedCertificates() {
        return certificateRepository.findByDeletedTrue();
    }

//    @Override
//    @Transactional
//    public void softDeleteCertificate(Long id) {
//        Certificate cert = certificateRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Certificate not found"));
//        cert.setDeleted(true);
//        certificateRepository.save(cert);
//    }

    @Override
    @Transactional
    public void softDeleteCertificate(Long id) {
        Certificate cert = certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found with id: " + id));

        if (cert.isDeleted()) {
            throw new IllegalStateException("Certificate already deleted");
        }

        cert.setDeleted(true);
        certificateRepository.save(cert);
    }

    @Override
    @Transactional
    public void restoreCertificate(Long id) {
        Certificate cert = certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
        cert.setDeleted(false);
        certificateRepository.save(cert);
    }

    @Override
    @Transactional
    public void hardDeleteCertificate(Long id) {
        Certificate cert = certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
        certificateRepository.delete(cert);
    }

    @Override
    public Certificate save(Certificate certificate) {
        return certificateRepository.save(certificate);
    }


    //mpyaaa aaaa aaaaa

    @Override
    public List<Certificate> getCertificatesByUserId(Long userId) {
        // 1. Hakikisha user exists (optional check)
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        // 2. Rudisha certificates za user huyo (zisizo soft-deleted)
        return certificateRepository.findByUserIdAndDeletedFalse(userId);
    }
}

