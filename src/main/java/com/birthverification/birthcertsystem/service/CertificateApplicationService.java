package com.birthverification.birthcertsystem.service;

import com.birthverification.birthcertsystem.model.CertificateApplication;
import com.birthverification.birthcertsystem.repository.CertificateApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CertificateApplicationService {

    private final CertificateApplicationRepository applicationRepository;

    public CertificateApplicationService(CertificateApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    // 1. CRUD Operations
    public CertificateApplication createApplication(CertificateApplication application) {
        application.setApplicationDate(new Date());
        application.setStatus("PENDING");
        application.setDeleted(false);
        return applicationRepository.save(application);
    }

    public Optional<CertificateApplication> getApplicationById(Long id) {
        return applicationRepository.findById(id);
    }

    public List<CertificateApplication> getAllApplications() {
        return applicationRepository.findAll();
    }

    public CertificateApplication updateApplication(Long id, CertificateApplication updatedDetails) {
        return applicationRepository.findById(id)
                .map(existingApp -> {
                    existingApp.setFullName(updatedDetails.getFullName());
                    existingApp.setDateOfBirth(updatedDetails.getDateOfBirth());
                    existingApp.setPlaceOfBirth(updatedDetails.getPlaceOfBirth());
                    existingApp.setGender(updatedDetails.getGender());
                    existingApp.setNationality(updatedDetails.getNationality());
                    existingApp.setNidNumber(updatedDetails.getNidNumber());
                    existingApp.setFatherName(updatedDetails.getFatherName());
                    existingApp.setFatherNationality(updatedDetails.getFatherNationality());
                    existingApp.setMotherName(updatedDetails.getMotherName());
                    existingApp.setMotherNationality(updatedDetails.getMotherNationality());
                    existingApp.setPhone(updatedDetails.getPhone());
                    existingApp.setEmail(updatedDetails.getEmail());
                    existingApp.setAddress(updatedDetails.getAddress());
                    existingApp.setReason(updatedDetails.getReason());
                    existingApp.setStatus(updatedDetails.getStatus());
                    return applicationRepository.save(existingApp);
                })
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));
    }

    // 2. Soft Delete Operations
    public void moveToRecycleBin(Long id) {
        applicationRepository.findById(id).ifPresent(application -> {
            application.setDeleted(true);
            applicationRepository.save(application);
        });
    }

    public void restoreFromRecycleBin(Long id) {
        applicationRepository.findById(id).ifPresent(application -> {
            application.setDeleted(false);
            applicationRepository.save(application);
        });
    }

    // 3. Status Management
    public void approveApplication(Long id) {
        applicationRepository.findById(id).ifPresent(application -> {
            application.setStatus("APPROVED");
            applicationRepository.save(application);
        });
    }

    public void rejectApplication(Long id, String reason) {
        applicationRepository.findById(id).ifPresent(application -> {
            application.setStatus("REJECTED");
            application.setReason(reason);
            applicationRepository.save(application);
        });
    }

    // 4. Query Methods
    public List<CertificateApplication> getActiveApplications() {
        return applicationRepository.findByDeleted(false);
    }

    public List<CertificateApplication> getDeletedApplications() {
        return applicationRepository.findByDeleted(true);
    }

    public List<CertificateApplication> getApplicationsByStatus(String status) {
        return applicationRepository.findByStatus(status);
    }

    public List<CertificateApplication> searchByName(String name) {
        return applicationRepository.findByFullNameContainingIgnoreCase(name);
    }

    public List<CertificateApplication> searchByNid(String nidNumber) {
        return applicationRepository.findBynidNumber(nidNumber);
    }

    public List<CertificateApplication> searchByFatherName(String fatherName) {
        return applicationRepository.findByFatherNameContainingIgnoreCase(fatherName);
    }

    public List<CertificateApplication> searchByMotherName(String motherName) {
        return applicationRepository.findByMotherNameContainingIgnoreCase(motherName);
    }

    // 5. Validation Methods
    public boolean isPhoneNumberUnique(String phone) {
        return applicationRepository.findByPhone(phone).isEmpty();
    }

    public boolean isEmailUnique(String email) {
        return applicationRepository.findByEmail(email).isEmpty();
    }

    public boolean isNidUnique(String nidNumber) {
        return applicationRepository.findByNidNumber(nidNumber).isEmpty();
    }
}