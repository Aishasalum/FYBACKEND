package com.birthverification.birthcertsystem.controller;

import com.birthverification.birthcertsystem.model.CertificateApplication;
import com.birthverification.birthcertsystem.service.CertificateApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificate-applications")
@CrossOrigin(origins = "*")
public class CertificateApplicationController {

    private final CertificateApplicationService applicationService;

    public CertificateApplicationController(CertificateApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // 1. CRUD Endpoints
    @PostMapping
    public ResponseEntity<CertificateApplication> createApplication(@RequestBody CertificateApplication application) {
        CertificateApplication createdApp = applicationService.createApplication(application);
        return ResponseEntity.ok(createdApp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateApplication> getApplication(@PathVariable Long id) {
        return applicationService.getApplicationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CertificateApplication>> getAllApplications() {
        List<CertificateApplication> applications = applicationService.getAllApplications();
        return ResponseEntity.ok(applications);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificateApplication> updateApplication(
            @PathVariable Long id,
            @RequestBody CertificateApplication applicationDetails) {
        CertificateApplication updatedApp = applicationService.updateApplication(id, applicationDetails);
        return ResponseEntity.ok(updatedApp);
    }

    // 2. Soft Delete Endpoints
    @PutMapping("/{id}/move-to-recycle")
    public ResponseEntity<Void> moveToRecycleBin(@PathVariable Long id) {
        applicationService.moveToRecycleBin(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<Void> restoreFromRecycleBin(@PathVariable Long id) {
        applicationService.restoreFromRecycleBin(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recycle-bin")
    public ResponseEntity<List<CertificateApplication>> getRecycleBinApplications() {
        List<CertificateApplication> deletedApps = applicationService.getDeletedApplications();
        return ResponseEntity.ok(deletedApps);
    }

    @GetMapping("/active")
    public ResponseEntity<List<CertificateApplication>> getActiveApplications() {
        List<CertificateApplication> activeApps = applicationService.getActiveApplications();
        return ResponseEntity.ok(activeApps);
    }

    // 3. Status Management Endpoints
    @PutMapping("/{id}/approve")
    public ResponseEntity<Void> approveApplication(@PathVariable Long id) {
        applicationService.approveApplication(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Void> rejectApplication(
            @PathVariable Long id,
            @RequestParam String reason) {
        applicationService.rejectApplication(id, reason);
        return ResponseEntity.noContent().build();
    }

    // 4. Search Endpoints
    @GetMapping("/search/by-name")
    public ResponseEntity<List<CertificateApplication>> searchByName(
            @RequestParam String name) {
        List<CertificateApplication> results = applicationService.searchByName(name);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/search/by-nid")
    public ResponseEntity<List<CertificateApplication>> searchByNid(
            @RequestParam String nidNumber) {
        List<CertificateApplication> results = applicationService.searchByNid(nidNumber);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/search/by-father")
    public ResponseEntity<List<CertificateApplication>> searchByFatherName(
            @RequestParam String fatherName) {
        List<CertificateApplication> results = applicationService.searchByFatherName(fatherName);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/search/by-mother")
    public ResponseEntity<List<CertificateApplication>> searchByMotherName(
            @RequestParam String motherName) {
        List<CertificateApplication> results = applicationService.searchByMotherName(motherName);
        return ResponseEntity.ok(results);
    }

    // 5. Validation Endpoints
    @GetMapping("/validate/phone")
    public ResponseEntity<Boolean> isPhoneUnique(
            @RequestParam String phone) {
        boolean isUnique = applicationService.isPhoneNumberUnique(phone);
        return ResponseEntity.ok(isUnique);
    }

    @GetMapping("/validate/email")
    public ResponseEntity<Boolean> isEmailUnique(
            @RequestParam String email) {
        boolean isUnique = applicationService.isEmailUnique(email);
        return ResponseEntity.ok(isUnique);
    }

    @GetMapping("/validate/nid")
    public ResponseEntity<Boolean> isNidUnique(
            @RequestParam String nidNumber) {
        boolean isUnique = applicationService.isNidUnique(nidNumber);
        return ResponseEntity.ok(isUnique);
    }
}